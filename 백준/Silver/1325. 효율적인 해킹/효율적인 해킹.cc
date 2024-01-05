#include <iostream>
#include <vector>
#include <queue>
using namespace std;

vector<vector<int>> graph;
vector<int> cnt;
int N, M;
int maxCnt;

int bfs(int s) {
    queue<int> q;
    q.push(s);
    vector<bool> visited(N + 1, false);
    visited[s] = true;

    int cnt = 0;
    while (!q.empty()) {
        int cur = q.front();
        q.pop();

        for (int next : graph[cur]) {
            if (!visited[next]) {
                q.push(next);
                visited[next] = true;
                cnt++;
            }
        }
    }
    return cnt;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

    cin >> N >> M;
    graph.resize(N + 1);
    cnt.resize(N + 1);

    for (int i = 0; i < M; i++) {
        int a, b;
        cin >> a >> b;
        graph[b].push_back(a);
    }

    for (int i = 1; i <= N; i++) {
        int cn = bfs(i);
        cnt[i] = cn;
        maxCnt = max(maxCnt, cn);
    }

    for (int i = 1; i <= N; i++) {
        if (cnt[i] == maxCnt) {
            cout << i << " ";
        }
    }

    return 0;
}
