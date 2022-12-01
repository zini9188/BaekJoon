#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

/*
 *  N: 지도의 세로 크기
 *  M: 지도의 가로 크기
 *  (3 ≤ N, M ≤ 8)
 */
int N, M, answer = 0;
vector<vector<int>> lab(8, vector<int>(8));
vector<int> dx = {0, 0, 1, -1};
vector<int> dy = {1, -1, 0, 0};

/*
 * 입력을 받는 함수
 */
void input() {
    cin >> N >> M;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            cin >> lab[i][j];
        }
    }
}

struct Point {
    int x, y;

    Point(int x, int y) : x(x), y(y) {}
};

/*
 *  bfs를 이용하여 연구소에 퍼지는 바이러스를 확인
 */
void bfs(vector<vector<int>> tmp) {
    queue<Point> q;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            if (tmp[i][j] == 2) {
                q.push(Point(i, j));
            }
        }
    }

    while (!q.empty()) {
        int x = q.front().x;
        int y = q.front().y;
        q.pop();
        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < M && tmp[nextX][nextY] == 0) {
                q.push(Point(nextX, nextY));
                tmp[nextX][nextY] = 2;
            }
        }
    }

    int cnt = 0;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            if (tmp[i][j] == 0) {
                cnt++;
            }
        }
    }
    answer = max(cnt, answer);
}

/*
 * 벽 3개를 세울 때까지 dfs를 이용
 */
void dfs(vector<vector<int>> tmp, int x, int y, int cnt) {
    tmp[x][y] = 1;
    if (cnt == 0) {
        bfs(tmp);
        return;
    }

    for (int i = x; i < N; i++) {
        for (int j = 0; j < M; j++) {
            if (tmp[i][j] == 0) {
                dfs(tmp, i, j, cnt - 1);
            }
        }
    }
}

/*
 * 현재 x,y,z 좌표와 일 수를 저장하며 다음 토마토들에게 값을 전달
 */
void solution() {
    vector<vector<int>> tmp(8, vector<int>(8));
    tmp = lab;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            if (tmp[i][j] == 0) {
                dfs(tmp, i, j, 2);
            }
        }
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    input();
    solution();
    cout << answer;
}