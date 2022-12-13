#include <string>
#include <vector>
#include <iostream>
#include <map>
#include <queue>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int N, M;
    cin >> N >> M;

    map<int, vector<int>> graph;
    vector<int> sequence(32001);
    int u, v;
    for (int i = 0; i < M; i++) {
        cin >> u >> v;
        sequence[v]++;
        graph[u].push_back(v);
    }

    queue<int> q;
    for (int i = 1; i <= N; i++) {
        if (sequence[i] == 0)
            q.push(i);
    }

    while (!q.empty()) {
        int node = q.front();
        q.pop();        
        cout << node << " ";
        for (int nextNode: graph[node]) {
            sequence[nextNode]--;
            if (sequence[nextNode] == 0) q.push(nextNode);
        }
    }
}