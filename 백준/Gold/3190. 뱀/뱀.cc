#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

int N, K, L;
vector<vector<int>> board(101, vector<int>(101));

struct Snake {
    int x, y, d;
    deque<pair<int, int>> tail;
    // 동 북 서 남
    vector<int> dx = {0, -1, 0, 1};
    vector<int> dy = {1, 0, -1, 0};

    Snake() {
        // 초기 1,1 꼬리 0개, 동쪽
        x = y = 1;
        d = 0;
        tail.push_front({x, y});
        board[x][y] = 2;
    }

    bool move() {
        int nx = x + dx[d];
        int ny = y + dy[d];
        if (nx >= 1 && nx <= N && ny >= 1 && ny <= N && board[nx][ny] != 2) {
            if (board[nx][ny] == 1) { // 사과가 있으면 꼬리 추가
                board[nx][ny] = 2;
                tail.push_front({nx, ny});
            } else { // 없으면 꼬리 움직이기
                board[nx][ny] = 2;
                board[tail.back().first][tail.back().second] = 0;
                tail.pop_back();
                tail.push_front({nx, ny});
            }
            x = nx;
            y = ny;
            return true;
        }
        return false;
    }

    void rotateLeft() {
        d = (d + 1) % 4;
    }

    void rotateRight() {
        d = (d + 3) % 4;
    }
};


int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    vector<pair<int, int>> apple(101);
    vector<pair<int, char>> moving(101);

    cin >> N >> K;
    for (int i = 0; i < K; i++) { // 사과 좌표 입력
        cin >> apple[i].first >> apple[i].second;
        board[apple[i].first][apple[i].second] = 1;
    }
    cin >> L;
    for (int i = 0; i < L; i++) { // 움직임 좌표 입력
        cin >> moving[i].first >> moving[i].second;
    }

    Snake snake = Snake();
    int timer = 0;
    int index = 0;
    while (snake.move()) {
        timer++;
        if (moving[index].first == timer) {
            if (moving[index].second == 'D') {
                snake.rotateRight();
            } else {
                snake.rotateLeft();
            }
            index++;
        }
    }
    cout << timer + 1;
}