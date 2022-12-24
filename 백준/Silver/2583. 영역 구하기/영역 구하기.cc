#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <queue>

using namespace std;

vector<int> dx = {1, -1, 0, 0};
vector<int> dy = {0, 0, 1, -1};

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int M, N, K;
    int x1, x2, y1, y2;
    vector<vector<int>> board(100, vector<int>(100, 0));
    vector<int> answer;
    cin >> M >> N >> K;

    for (int i = 0; i < K; i++) {
        cin >> x1 >> y1 >> x2 >> y2;
        for (int j = x1; j < x2; j++) {
            for (int k = y1; k < y2; k++) {
                if (!board[j][k])
                    board[j][k] = 1;
            }
        }
    }
    vector<vector<int>> visit(100, vector<int>(100, 0));
    queue<pair<int, int>> q;
    
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            if (!visit[i][j] && !board[i][j]) {
                q.push({i, j});
                visit[i][j] = 1;
                int count = 0;
                while (!q.empty()) {
                    count++;
                    int x = q.front().first;
                    int y = q.front().second;
                    q.pop();

                    for (int k = 0; k < 4; k++) {
                        int nx = x + dx[k];
                        int ny = y + dy[k];
                        if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visit[nx][ny] && !board[nx][ny]) {
                            visit[nx][ny] = 1;
                            q.push({nx, ny});
                        }
                    }
                }
                answer.push_back(count);
            }
        }
    }
    cout << answer.size() << '\n';
    sort(answer.begin(), answer.end());
    for (auto ans: answer) {
        cout << ans << ' ';
    }
}
