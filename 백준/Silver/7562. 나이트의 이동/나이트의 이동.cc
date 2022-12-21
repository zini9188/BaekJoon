#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <queue>

using namespace std;

vector<int> dx = {-2, -2, -1, -1, 1, 1, 2, 2};
vector<int> dy = {1, -1, 2, -2, 2, -2, 1, -1};

struct point {
    int x, y, count;

    point(int x, int y, int count) : x(x), y(y), count(count) {}
};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int T, l, x, y, nx, ny;
    cin >> T;
    for (int i = 0; i < T; i++) {
        cin >> l;
        cin >> x >> y;
        cin >> nx >> ny;
        int answer = 987654321;
        queue<point> q;
        q.push({x, y, 0});
        vector<vector<int>> visited(l, vector<int>(l, 0));

        while (!q.empty()) {
            int cx = q.front().x;
            int cy = q.front().y;
            int cnt = q.front().count;
            q.pop();
            if (cx == nx && cy == ny) answer = min(answer, cnt);
            
            for (int j = 0; j < 8; j++) {
                int nextX = cx + dx[j];
                int nextY = cy + dy[j];
                if (nextX >= 0 && nextX < l && nextY >= 0 && nextY < l && !visited[nextX][nextY]) {
                    visited[nextX][nextY] = 1;
                    q.push({nextX, nextY, cnt + 1});
                }
            }

        }
        cout << answer << endl;
    }
}
