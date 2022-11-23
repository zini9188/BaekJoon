#include <iostream>
using namespace std;
int arr[8];
int n, m;

void print() {
    for (int i = 0; i < m; i++)
        cout << arr[i] << ' ';
    cout << '\n';
    return;
}

void DFS(int cnt, int idx) {
    if (idx == m) {
        print();
        return;
    }
    for (int i = cnt; i < n; i++) {
        arr[idx] = i + 1;
        DFS(i, idx + 1);
    }
}

int main() {
    cin >> n >> m;

    DFS(0, 0);
}
