#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

struct Point {
    int x, y, count, flag;

    Point(int x, int y, int count, int flag) : x(x), y(y), count(count), flag(flag) {}
};

vector<int> dx = {1, 0, 0, -1};
vector<int> dy = {0, 1, -1, 0};
vector<vector<vector<int>>> visited(2, vector<vector<int>>(1001, vector<int>(1001)));

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int N, M, answer = 987654321;
    vector<string> map(1001);

    cin >> N >> M;
    for (int i = 0; i < N; i++) {
        cin >> map[i];
    }

    queue<Point> q;
    q.push({0, 0, 1, 0});
    visited[0][0][0] = 1;
    while (!q.empty()) {
        int x = q.front().x;
        int y = q.front().y;
        int count = q.front().count;
        int flag = q.front().flag;
        q.pop();
        
        if (x == N - 1 && y == M - 1) {
            answer = min(count, answer);
        }
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                if (map[nx][ny] == '1' && flag == 0) {
                    visited[flag + 1][nx][ny] = 1;
                    q.push({nx, ny, count + 1, 1});
                }
                if (map[nx][ny] == '0' && !visited[flag][nx][ny]) {
                    visited[flag][nx][ny] = 1;
                    q.push({nx, ny, count + 1, flag});
                }

            }
        }
    }
    answer = answer == 987654321 ? -1 : answer;
    cout << answer;
}