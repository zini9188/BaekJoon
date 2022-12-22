#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;
int nodes[1000001];

int getParent(int x) {
    if (nodes[x] == x) return x;
    return nodes[x] = getParent(nodes[x]);
}

void unionParent(int a, int b) {
    a = getParent(a);
    b = getParent(b);
    if (a > b) nodes[a] = b;
    else nodes[b] = a;
}

void findParent(int a, int b) {
    a = getParent(a);
    b = getParent(b);
    if (a == b) cout << "YES\n";
    else cout << "NO\n";
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int n, m;
    cin >> n >> m;
    int flag, a, b;
    for (int i = 0; i <= n; i++) nodes[i] = i;

    for (int i = 0; i < m; i++) { 
        cin >> flag >> a >> b;
        if (flag) findParent(a, b);
        else unionParent(a, b);
    }

}
