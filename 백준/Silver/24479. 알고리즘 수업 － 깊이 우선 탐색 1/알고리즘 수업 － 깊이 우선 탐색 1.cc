#include <iostream>
#include <vector>
#include <map>
#include <algorithm>

using namespace std;

bool visited[200001];
map<int, vector<int>> graph;
int index[100001];
int I;

void dfs(int R) {
    index[R] = ++I;
    visited[R] = true;
    for (int i = 0; i < graph[R].size(); i++) {
        if (!visited[graph[R][i]])
            dfs(graph[R][i]);
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int N, M, R; // (정점, 간선, 시작 정점)
    cin >> N >> M >> R;

    int u, v;
    for (int i = 0; i < M; i++) {
        cin >> u >> v;
        graph[u].push_back(v);
        graph[v].push_back(u);
    }

    for (auto &g: graph) {
        sort(g.second.begin(), g.second.end());
    }

    dfs(R);

    for(int i = 1; i <= N; i++){
        cout << index[i] << "\n";
    }
}