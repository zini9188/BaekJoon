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

    vector<pair<int, int>> graph[20001]; // 그래프를 저장할 map collection
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
        int dist = -pq.top().first; // 가장 아래의 값을 가져오기 위함 (이를 사용하고 싶지 않다면 pq선언에서 greast사용
        pq.pop();

        // 현재 노드에서 다음 정점까지의 거리 최단경로를 구하기
        for (auto item: graph[node]) {
            int nextDist = dist + item.second;
            int nextNode = item.first;
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