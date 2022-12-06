#include <iostream>
#include <vector>
#include <algorithm>
#include <map>
#include <queue>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    map<int, vector<int>> graph;
    vector<bool> visited(1001);
    int N, M, answer = 0,u, v;
    cin >> N >> M;
    for (int i = 0; i < M; i++) {
        cin >> u >> v;
        graph[u].push_back(v);
        graph[v].push_back(u);
    }

    for (int i = 1; i <= N; i++) {
        if (!visited[i]) {
            queue<int> q;
            q.push(i);
            visited[i] = true;
            while (!q.empty()) {
                int node = q.front();
                q.pop();
                for (int j = 0; j < graph[node].size(); j++) {
                    int nextNode = graph[node][j];
                    if (!visited[nextNode]) {
                        visited[nextNode] = true;
                        q.push(nextNode);
                    }
                }
            }
            answer++;
        }
    }
    cout << answer;
}