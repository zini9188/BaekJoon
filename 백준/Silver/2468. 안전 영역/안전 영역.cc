#include <string>
#include <iostream>
#include <vector>
#include <map>
#include <queue>

using namespace std;
vector<int> dx = {1, 0, -1, 0};
vector<int> dy = {0, -1, 0, 1};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int n, answer = 0, h = 0;
    cin >> n;
    vector<vector<int>> board(n, vector<int>(n, 0));
    map<int, vector<pair<int, int>>> sink;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            cin >> board[i][j];
            sink[board[i][j]].push_back({i, j});
            h = max(h, board[i][j]);
        }
    }

    for (int i = 0; i < h; i++) { // 물의 높이
        vector<vector<int>> visited(n, vector<int>(n, 0));
        for (auto xy: sink[i]) board[xy.first][xy.second] = 0;
        int cnt = 0;
        for (int j = 0; j < n; j++) {
            for (int k = 0; k < n; k++) {
                if (board[j][k] && !visited[j][k]) {
                    cnt++;
                    queue<pair<int, int>> q;
                    q.push({j, k});
                    visited[j][k] = 1;
                    while (!q.empty()) {
                        int x = q.front().first;
                        int y = q.front().second;
                        q.pop();

                        for (int l = 0; l < 4; l++) {
                            int nx = x + dx[l];
                            int ny = y + dy[l];
                            if (nx >= 0 && nx < n && ny >= 0 && ny < n && board[nx][ny] && !visited[nx][ny]) {
                                visited[nx][ny] = 1;
                                q.push({nx, ny});
                            }
                        }
                    }
                }
            }
        }
        answer = max(answer, cnt);
    }
    cout << answer;
}
