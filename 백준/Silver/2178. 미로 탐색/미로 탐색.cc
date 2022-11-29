#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

/*
 *  기본 변수들 선언
 */
int N, M, answer = 987654321;
vector<string> board(101);
vector<vector<bool>> visited(101, vector<bool>(101, false));
vector<int> dirX = {1, 0, 0, -1};
vector<int> dirY = {0, 1, -1, 0};

/*
 * Point struct : x, y와 해당하는 좌표까지 이동한 거리의 가중치 저장
 */
struct Point {
public :
    int x, y, move;

    Point(int x_, int y_, int move_) {
        x = x_;
        y = y_;
        move = move_;
    }
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> N >> M;
    for (int i = 0; i < N; i++) {
        cin >> board[i];
    }

    /*
     * bfs를 이용하여 풀이
     */
    queue<Point> q;
    q.push(Point(0, 0, 1));
    visited[0][0] = true;
    while (!q.empty()) {
        int curX = q.front().x;
        int curY = q.front().y;
        int curMove = q.front().move;
        q.pop();

        if (curX == N - 1 && curY == M - 1) {
            answer = min(answer, curMove);
        }

        for (int i = 0; i < 4; i++) {
            int nextX = curX + dirX[i];
            int nextY = curY + dirY[i];
            if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < M && !visited[nextX][nextY] &&
                board[nextX][nextY] == '1') {
                visited[nextX][nextY] = true;
                q.push(Point(nextX, nextY, curMove + 1));
            }
        }
    }
    cout << answer;
}