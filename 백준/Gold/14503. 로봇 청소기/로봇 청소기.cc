#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;
// 북 동 남 서
vector<int> dx = {-1, 0, 1, 0};
vector<int> dy = {0, 1, 0, -1};

int N, M;
vector<vector<int>> map(51, vector<int>(51));
vector<vector<int>> visited(51, vector<int>(51));

void dfs(int x, int y, int d, int count) {

    // 2. 현재 위치에서 현재 방향을 기준으로 왼쪽방향부터 차례대로 탐색을 진행한다
    for (int i = 0; i < 4; i++) {
        // 2-2. 왼쪽 방향에 청소할 공간이 없다면, 그 방향으로 회전하고 2번으로 돌아간다.
        int nd = (d + 3 - i) % 4;
        int nr = x + dx[nd];
        int nc = y + dy[nd];
        if (nr < 0 || nr >= N || nc >= M || nc < 0 || map[nr][nc] == 1)
            continue;

        // 2-1. 왼쪽 방향에 아직 청소하지 않은 공간이 존재한다면, 그 방향으로 회전한 다음 한 칸을 전진하고 1번부터 진행한다.
        if (visited[nr][nc] == 0 && map[nr][nc] == 0) {
            visited[nr][nc] = 1;
            dfs(nr, nc, nd, count + 1);
        }
    }


    int bd = (d + 2) % 4;
    int bx = x + dx[bd];
    int by = y + dy[bd];
    if (bx >= 0 && bx < N && by >= 0 && by < M) {
        // 3. 네 방향 모두 청소가 이미 되어있거나 벽인 경우에는, 바라보는 방향을 유지한 채로 한 칸 후진을 하고 2번으로 돌아간다.
        if (map[bx][by] == 0) {
            dfs(bx, by, d, count);
        } // 4. 네 방향 모두 청소가 이미 되어있거나 벽이면서, 뒤쪽 방향이 벽이라 후진도 할 수 없는 경우에는 작동을 멈춘다.
        else {
            cout << count << endl;
            exit(0);
        }
    }

}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int r, c, d;
    cin >> N >> M;
    cin >> r >> c >> d;

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            cin >> map[i][j];
        }
    }
    visited[r][c] = 1;
    dfs(r, c, d, 1);
}