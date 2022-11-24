#include <iostream>
#include <vector>
#include <map>
#include <algorithm>
#include <queue>
#include <cstring>

using namespace std;
map<int, vector<int>> graph;
vector<bool> visited(1002, false);

void dfs(int node) {
    visited[node] = true;
    cout << node << " ";
    for (int conn: graph[node]) {
        if (!visited[conn]) {
            visited[conn] = true;
            dfs(conn);
        }
    }
}

void bfs(int V) {
    queue<int> q;
    q.push(V);
    visited[V] = true;
    while (!q.empty()) {
        int node = q.front();
        q.pop();
        cout << node << " ";

        for (int &nextNode: graph[node]) {
            if (!visited[nextNode]) {
                visited[nextNode] = true;
                q.push(nextNode);
            }
        }
    }
}

int main() {
    int N, M, V;
    cin >> N >> M >> V;
    int left, right;

    for (int i = 0; i < M; i++) {
        cin >> left >> right;
        graph[left].push_back(right);
        graph[right].push_back(left);
    }
    for (auto &a: graph) {
        sort(a.second.begin(), a.second.end());
    }

    dfs(V);
    cout << endl;
    fill(visited.begin(), visited.end(), false);
    bfs(V);
}


