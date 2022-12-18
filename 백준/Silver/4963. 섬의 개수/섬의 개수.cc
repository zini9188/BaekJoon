#include <string>
#include <iostream>
#include <vector>
#include <queue>

using namespace std;

vector<int> dx = {1, 1, 1, -1, -1, -1, 0, 0};
vector<int> dy = {-1, 0, 1, -1, 0, 1, -1, 1};

int newLand(int w, int h) {
    vector<vector<int>> v(h, vector<int>(w, 0));
    vector<vector<int>> visited(h, vector<int>(w, 0));
    vector<pair<int, int>> ls;
    queue<pair<int, int>> q;
    int answer = 0;

    for (int i = 0; i < h; i++) {
        for (int j = 0; j < w; j++) {
            cin >> v[i][j];
            if (v[i][j] == 1) {
                ls.emplace_back(i, j);
            }
        }
    }

    for (auto l: ls) {
        if (!visited[l.first][l.second]) {
            answer++;
            visited[l.first][l.second] = 1;
            q.push({l.first, l.second});
            while (!q.empty()) {
                int x = q.front().first;
                int y = q.front().second;
                q.pop();

                for (int i = 0; i < 8; i++) {
                    int nextX = x + dx[i];
                    int nextY = y + dy[i];
                    if (nextX >= 0 && nextX < h && nextY >= 0 && nextY < w && v[nextX][nextY] == 1 &&
                        !visited[nextX][nextY]) {
                        visited[nextX][nextY] = 1;
                        q.push({nextX, nextY});
                    }
                }
            }
        }
    }
    return answer;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    while (true) {
        int w, h;
        cin >> w >> h;
        if (w == 0 && h == 0) break;
        cout << newLand(w, h) << '\n';
    }
}