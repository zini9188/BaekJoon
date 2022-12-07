#include <iostream>
#include <vector>
#include <map>
#include <queue>

#define INF 987654321
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int N, M;
    cin >> N >> M;
    map<int, vector<pair<int, int>>> cities;
    vector<int> dists(1001);
    fill(dists.begin(), dists.end(), INF);

    int from, to, dist;
    for (int i = 0; i < M; i++) {
        cin >> from >> to >> dist;
        cities[from].push_back(make_pair(to, dist));
    }
    int start, end;
    cin >> start >> end;

    priority_queue<pair<int, int>> pq;
    pq.push({0, start});
    dists[start] = 0;
    while (!pq.empty()) {
        int currentDist = -pq.top().first;
        int currentNode = pq.top().second;
        pq.pop();

        if (currentDist > dists[currentNode]) {
            continue;
        }

        for (auto city: cities[currentNode]) {
            int nextNode = city.first;
            int nextDist = city.second + currentDist;
            if (dists[nextNode] > nextDist) {
                dists[nextNode] = nextDist;
                pq.push({-nextDist, nextNode});
            }
        }

    }
    cout << dists[end];
}

