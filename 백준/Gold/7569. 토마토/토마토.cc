#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

/*
 *  x,y 좌표와 날짜를 관리하는 구조체
 */
struct Point {
    int z, x, y, day;

    Point(int z, int x, int y, int day) : z(z), x(x), y(y), day(day) {}

};

/*
 *  H: 쌓아올려지는 상자의 수
 *  M: 상자의 가로 칸의 수
 *  N: 상자의 세로 칸의 수
 *  (2 ≤ M ≤ 100, 2 ≤ N ≤ 100, 1 ≤ H ≤ 100)
 */
int M, N, H, answer = 0, cnt = 0;
vector<vector<vector<int>>> box(101, vector<vector<int>>(101, vector<int>(101)));
vector<vector<vector<int>>> visited(101, vector<vector<int>>(101, vector<int>(101)));
// 위 아래 왼쪽 오른쪽 앞 뒤
vector<int> dirX = {0, 0, 1, 0, 0, -1};
vector<int> dirY = {0, 0, 0, 1, -1, 0};
vector<int> dirZ = {1, -1, 0, 0, 0, 0};
queue<Point> q;

/*
 * 입력을 받는 함수
 * 1인경우 토마토가 이미 있는 곳이므로 시작할 위치를 queue에 넣어주고 방문처리.
 * -1인경우 토마토가 갈 수 없는 곳으로 방문처리
 */
void input() {
    cin >> M >> N >> H;
    for (int i = 0; i < H; i++) {
        for (int j = 0; j < N; j++) {
            for (int k = 0; k < M; k++) {
                cin >> box[i][j][k];
                if (box[i][j][k] == 1) {
                    q.push(Point(i, j, k, 0));
                    visited[i][j][k] = 1;
                    cnt++;
                } else if (box[i][j][k] == -1) {
                    visited[i][j][k] = 1;
                    cnt++;
                }
            }
        }
    }
}

/*
 * 현재 x,y,z 좌표와 일 수를 저장하며 다음 토마토들에게 값을 전달
 */
int solution() {
    while (!q.empty()) {
        int curX = q.front().x;
        int curY = q.front().y;
        int curZ = q.front().z;
        int curDay = q.front().day;
        q.pop();
        answer = max(curDay, answer);
        for (int l = 0; l < 6; l++) {
            int nextX = curX + dirX[l];
            int nextY = curY + dirY[l];
            int nextZ = curZ + dirZ[l];
            if (nextX >= 0 && nextX < N && nextY >= 0 &&
                nextY < M && nextZ >= 0 && nextZ < H &&
                box[nextZ][nextX][nextY] == 0 && !visited[nextZ][nextX][nextY]) {
                visited[nextZ][nextX][nextY] = 1;
                q.push(Point(nextZ, nextX, nextY, curDay + 1));
                cnt++;
            }
        }
    }
    answer = cnt == M * N * H ? answer : -1;
    return answer;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    input();
    cout << solution();
}