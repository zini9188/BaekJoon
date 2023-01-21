#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <queue>
// B
using namespace std;
int dx[4] = {1, 0, 0, -1};
int dy[4] = {0, 1, -1, 0};
int N, M;

bool endOfMap(int x, int y) {
    return x == -1 || x == N || y == -1 || y == M;
}

pair<int, int> nextPos(int x, int y) {
    if (x == -1) {
        return {N - 1, y};
    } else if (y == -1) {
        return {x, M - 1};
    } else if (x == N) {
        return {0, y};
    } else {
        return {x, 0};
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int result = 0;
    cin >> N >> M;
    vector<vector<int>> planet(1001, vector<int>(1001, 0));
    vector<vector<int>> visited(1001, vector<int>(1001, 0));
    vector<pair<int, int>> startPos;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            cin >> planet[i][j];
            if (planet[i][j] == 0) startPos.emplace_back(i, j);
            else visited[i][j] = true;
        }
    }
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            if (!visited[i][j]) {
                visited[i][j] = true;
                queue<pair<int, int>> q;
                q.push({i, j});
                while (!q.empty()) {
                    int x = q.front().first;
                    int y = q.front().second;
                    q.pop();
                    for (int k = 0; k < 4; k++) {
                        int nx = x + dx[k];
                        int ny = y + dy[k];
                        if (endOfMap(nx, ny)) {
                            pair<int, int> np = nextPos(nx, ny);
                            nx = np.first;
                            ny = np.second;
                        }
                        if (!visited[nx][ny]) {
                            visited[nx][ny] = true;
                            q.push({nx, ny});
                        }
                    }
                }
                result++;
            }
        }
    }
    cout << result;
}