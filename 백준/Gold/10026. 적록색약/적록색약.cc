#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;
int N;
vector<int> dx = {1, -1, 0, 0};
vector<int> dy = {0, 0, 1, -1};
vector<string> map;
vector<vector<bool>> visited(101, vector<bool>(101));

struct Point {
    int x, y;

    Point(int x, int y) : x(x), y(y) {}
};

void bfs(int x, int y) {
    queue<Point> q;

    q.push(Point(x, y));
    visited[x][y] = true;

    while (!q.empty()) {
        int curX = q.front().x;
        int curY = q.front().y;
        q.pop();

        for (int i = 0; i < 4; i++) {
            int nextX = curX + dx[i];
            int nextY = curY + dy[i];
            if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < N && !visited[nextX][nextY] &&
                map[nextX][nextY] == map[curX][curY]) {
                visited[nextX][nextY] = true;
                q.push(Point(nextX, nextY));
            }
        }
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> N;
    string line;
    int answer = 0, answer2 = 0;
    for (int i = 0; i < N; i++) {
        cin >> line;
        map.push_back(line);
    }

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if (!visited[i][j]) {
                bfs(i, j);
                answer++;
            }
        }
    }

    for (int i = 0; i < N; i++) {
        fill(visited[i].begin(), visited[i].begin() + N, false);
    }

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if (map[i][j] == 'G') map[i][j] = 'R';
        }
    }

    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if (!visited[i][j]) {
                bfs(i, j);
                answer2++;
            }
        }
    }
    cout << answer << '\n' << answer2;
}