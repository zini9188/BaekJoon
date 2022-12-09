#include <string>
#include <vector>
#include <iostream>
#include <queue>

using namespace std;
int n, answer = 0;

int getMax(vector<vector<int>> board) {
    int num = 0;
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            num = max(board[i][j], num);
        }
    }
    return num;
}

vector<vector<int>> north(vector<vector<int>> board) { // 위로
    for (int i = 1; i <= n; i++) {
        deque<int> q;
        int flag = 0;
        for (int j = 1; j <= n; j++) {
            if (board[j][i] > 0) { // 0보다 큰 경우만
                if (q.empty()) { // 비어있으면 그냥 넣기
                    q.push_back(board[j][i]);
                } else { // 비어있지 않은 경우
                    if (q.back() == board[j][i] && flag == 0) { // 뒤의 값이 같은 값이면
                        flag = 1;
                        q.pop_back();
                        q.push_back(board[j][i] * 2);
                    } else { // 그렇지 않으면
                        q.push_back(board[j][i]);
                        flag = 0;
                    }

                }
            }
        }
        while (q.size() < n) {
            q.push_back(0);
        }
        for (int j = 1; j <= n; j++) {
            board[j][i] = q.front();
            q.pop_front();
        }
    }
    return board;
}

vector<vector<int>> south(vector<vector<int>> board) { // 아래로
    for (int i = 1; i <= n; i++) {
        deque<int> q;
        int flag = 0;
        for (int j = n; j >= 1; j--) {
            if (board[j][i] > 0) { // 0보다 큰 경우만
                if (q.empty()) { // 비어있으면 그냥 넣기
                    q.push_back(board[j][i]);
                } else { // 비어있지 않은 경우
                    if (q.back() == board[j][i] && flag == 0) { // 뒤의 값이 같은 값이면
                        flag = 1;
                        q.pop_back();
                        q.push_back(board[j][i] * 2);
                    } else { // 그렇지 않으면
                        q.push_back(board[j][i]);
                        flag = 0;

                    }
                }
            }
        }
        while (q.size() < n) {
            q.push_back(0);
        }
        for (int j = n; j >= 1; j--) {
            board[j][i] = q.front();
            q.pop_front();
        }
    }
    return board;
}

vector<vector<int>> west(vector<vector<int>> board) { // 왼쪽으로
    for (int i = 1; i <= n; i++) {
        deque<int> q;
        int flag = 0;
        for (int j = 1; j <= n; j++) {
            if (board[i][j] > 0) { // 0보다 큰 경우만
                if (q.empty()) { // 비어있으면 그냥 넣기
                    q.push_back(board[i][j]);
                } else { // 비어있지 않은 경우
                    // 마지막이 아니면
                    if (q.back() == board[i][j] && flag == 0) { // 뒤의 값이 같은 값이면
                        flag = 1;
                        q.pop_back();
                        q.push_back(board[i][j] * 2);
                    } else { // 그렇지 않으면
                        q.push_back(board[i][j]);
                        flag = 0;
                    }

                }
            }
        }
        while (q.size() < n) {
            q.push_back(0);
        }
        for (int j = 1; j <= n; j++) {
            board[i][j] = q.front();
            q.pop_front();
        }
    }
    return board;
}

vector<vector<int>> east(vector<vector<int>> board) { // 오른쪽으로
    for (int i = 1; i <= n; i++) {
        deque<int> q;
        int flag = 0;
        for (int j = n; j >= 1; j--) {
            if (board[i][j] > 0) { // 0보다 큰 경우만
                if (q.empty()) { // 비어있으면 그냥 넣기
                    q.push_back(board[i][j]);
                } else { // 비어있지 않은 경우
                    if (q.back() == board[i][j] && flag == 0) { // 뒤의 값이 같은 값이면
                        flag = 1;
                        q.pop_back();
                        q.push_back(board[i][j] * 2);
                    } else { // 그렇지 않으면
                        q.push_back(board[i][j]);
                        flag = 0;
                    }
                }
            }
        }
        while (q.size() < n) {
            q.push_back(0);
        }
        for (int j = n; j >= 1; j--) {
            board[i][j] = q.front();
            q.pop_front();
        }
    }
    return board;
}
void dfs(vector<vector<int>> board, int count, string dir) {
    if (count == 5) {
        answer = max(answer, getMax(board));
        return;
    }

    dfs(east(board), count + 1, dir + "e");
    dfs(west(board), count + 1, dir + "w");
    dfs(north(board), count + 1, dir + "n");
    dfs(south(board), count + 1 , dir + "s");
}


int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    vector<vector<int>> board(21, vector<int>(21));
    cin >> n;

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            cin >> board[i][j];
        }
    }

    dfs(board, 0, "");
    cout << answer;
}