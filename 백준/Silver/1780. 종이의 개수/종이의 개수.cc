#include <iostream>
using namespace std;

int n;
int board[2200][2200];
int ans[3];

void dq(int x, int y, int inc) {
    bool comp = true;
    // -1, 0, 1 모든 값이 같은 경우를 판별
    for (int i = x; i < x + inc; i++) {
        for (int j = y; j < y + inc; j++) {
            if (board[i][j] != board[x][y]) {
                comp = false;
            }
        }
    }
    // 값이 같으면 증가 및 리턴
    if (comp) {
        ans[board[x][y] + 1]++;
        return;
    }

    int div = inc / 3;
    // 9등분
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            dq(x + div * i, y + div * j, div);
        }
    }
}

int main() {
    cin >> n;
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            cin >> board[i][j];
        }
    }

    dq(1, 1, n);

    for (int i = 0; i < 3; i++)
        cout << ans[i] << '\n';
}