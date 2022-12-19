#include <string>
#include <iostream>
#include <vector>
#include <queue>

using namespace std;

vector<int> winning;
vector<int> visited(49);

void printQ() {
    for (int i = 0; i < 6; i++) {
        cout << winning[i] << " ";
    }
    cout << '\n';
}

void dfs(int index, vector<int> v, int cnt) {
    if (cnt == 6) {
        printQ();
        return;
    }
    for (int i = index; i < v.size(); i++) {
        if (!visited[i]) {
            visited[i] = 1;
            winning.push_back(v[i]);
            dfs(i + 1, v, cnt + 1);
            winning.pop_back();
            visited[i] = 0;
        }
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int n;
    while (true) {
        cin >> n;
        if(n == 0) break;
        vector<int> lotto(n);
        for (int i = 0; i < n; i++) cin >> lotto[i];
        dfs(0, lotto, 0);
        cout << '\n';
    }
}
