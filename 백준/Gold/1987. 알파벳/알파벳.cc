#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

int R, C, answer = 0;
vector<int> dx = {1, -1, 0, 0};
vector<int> dy = {0, 0, -1, 1};
vector<bool> alphabet(27);
vector<string> board(21);

void dfs(int x, int y, int dist) {
    answer = max(dist, answer);
    for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];
        if (nx >= 0 && nx < R && ny >= 0 && ny < C && !alphabet[board[nx][ny] - 'A']) {
            alphabet[board[nx][ny] - 'A'] = true;
            dfs(nx, ny, dist + 1);
            alphabet[board[nx][ny] - 'A'] = false;
        }
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> R >> C;
    for (int i = 0; i < R; i++) {
        cin >> board[i];
    }
    alphabet[board[0][0] - 'A'] = true;
    dfs(0, 0, 1);
    cout << answer;
}
