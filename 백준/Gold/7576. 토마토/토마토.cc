#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;
/*
 *  기본 입력 변수
 *  방문 개수를 셀 cnt
 */
int M, N, answer = 0, cnt = 0;
vector<vector<int>> box(1001, vector<int>(1001));
vector<vector<int>> visited(1001, vector<int>(1001));
vector<int> dirX = {1, 0, 0, -1};
vector<int> dirY = {0, 1, -1, 0};
/*
 *  x,y 좌표와 날짜를 관리하는 구조체
 */
struct Point {
    int x, y, day;

    Point(int x, int y, int day) : x(x), y(y), day(day) {}
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> M >> N;
    /*
     * 입력을 받을 때 1인 경우 queue에 넣어주고 방문 처리
     */
    queue<Point> q;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            cin >> box[i][j];
            if (box[i][j] == 1) {
                q.push(Point(i, j, 0));
                visited[i][j] = 1;
                cnt++;
            } else if (box[i][j] == -1) {
                visited[i][j] = 1;
                cnt++;
            }
        }
    }

    while (!q.empty()) {
        int curX = q.front().x;
        int curY = q.front().y;
        int curDay = q.front().day;
        q.pop();
        answer = max(curDay, answer);

        for (int i = 0; i < 4; i++) {
            int nextX = curX + dirX[i];
            int nextY = curY + dirY[i];
            if (nextX >= 0 && nextX < N && nextY >= 0 &&
                nextY < M && box[nextX][nextY] == 0 && !visited[nextX][nextY]) {
                visited[nextX][nextY] = 1;
                q.push(Point(nextX, nextY, curDay + 1));
                cnt++;
            }
        }
    }
    answer = cnt == M * N ? answer : -1;
    cout << answer;
}