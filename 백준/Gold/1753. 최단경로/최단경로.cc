#include <iostream>
#include <vector>
#include <map>
#include <queue>

using namespace std;
const int INF = 987654321;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int V, E, K;
    cin >> V >> E >> K; // (정점, 간선, 시작 정점)

    vector<pair<int, int>> graph[20001]; // 그래프를 저장할 배열
    int u, v, w; // (u에서 v로 가는 가중치 w인 간선)
    for (int i = 0; i < E; i++) {
        cin >> u >> v >> w;
        graph[u].emplace_back(v, w);
    }

    priority_queue<pair<int,int>>pq; // <비용, 노드> 형식의 priority_queue
    pq.push(make_pair(0, K)); // 처음 시작할 위치와 가중치
    vector<int> distance(V + 1, INF); // 거리의 최소를 구할 vector INF로 초기화
    distance[K] = 0; // 시작 노드에서 시작 노드로 가는 비용 0

    while (!pq.empty()) {
        int node = pq.top().second;
        int dist = -pq.top().first; // 현재는 오름차순으로 정렬되어 있으므로 가장 아래의 값을 가져오기 위함
        pq.pop();

        // 현재 노드에서 다음 정점까지의 거리 최단경로를 구하기
        for (auto item: graph[node]) {
            int nextDist = dist + item.second;
            int nextNode = item.first;
            // 다음 노드가 저장된 다음 노드의 가중치보다 작다면 갱신한다.
            if (nextDist < distance[nextNode]) { 
                distance[nextNode] = nextDist;
                pq.push(make_pair(-nextDist, nextNode));
            }
        }
    }

    for (int i = 1; i <= V; i++) {
        if (distance[i] == INF) {
            cout << "INF\n";
        } else {
            cout << distance[i] << "\n";
        }
    }
}
