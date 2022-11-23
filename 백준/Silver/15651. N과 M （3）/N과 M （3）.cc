#include <iostream>
using namespace std;

int arr[8];
bool visited[8];
int n, m;

void dfs(int cnt, int idx) {
    if (cnt == m) {
        for (int i = 0; i < m; i++) {
            cout << arr[i] << ' ';
        }
        cout << '\n';
        return;
    }

    for (int i = 0; i < n; i++) {
        visited[i] = true;
        arr[cnt] = i + 1;
        dfs(cnt + 1, i + 1);
        visited[i] = false;
    }
}

int main() {
    cin >> n >> m;

    dfs(0, 0);
}