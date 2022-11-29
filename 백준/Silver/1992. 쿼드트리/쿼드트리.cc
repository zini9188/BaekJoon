#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

vector<string> tree(65);
void quadTree(int x, int y, int N) {
    char cur = tree[x][y];
    for (int i = x; i < x + N; i++) {
        for (int j = y; j < y + N; j++) {
            if (tree[i][j] != cur) { // 다른 값이 나오면 그 사각형을 분리한다.
                cout << "(";
                // 4사분면 전체를 나눠서 실행 시킨다.
                quadTree(x, y, N / 2);
                quadTree(x, y + N / 2, N / 2);
                quadTree(x + N / 2, y, N / 2);
                quadTree(x + N / 2, y + N / 2, N / 2);
                cout << ")";
                return;
            }
        }
    }
    cout << cur;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int N;
    cin >> N;

    for (int i = 0; i < N; i++) {
        cin >> tree[i];
    }

    quadTree(0, 0, N);
}