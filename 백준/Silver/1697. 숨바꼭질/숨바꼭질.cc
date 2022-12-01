#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;
#define MAX_SIZE 100001

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int N, K, answer = 987654321;
    cin >> N >> K;
    vector<int> visited(MAX_SIZE);
    priority_queue<pair<int, int>> pq;
    pq.push({0, -N});
    visited[N] = 1;

    while (!pq.empty()) {
        int second = -pq.top().first;
        int node = -pq.top().second;
        pq.pop();

        if (node == K) {
            answer = min(second, answer);
        }
        if (node * 2 <= MAX_SIZE && !visited[node * 2]) {
            pq.push({-(second + 1), -node * 2});
            visited[node * 2] = 1;
        }
        if (node + 1 <= MAX_SIZE && !visited[node + 1]) {
            pq.push({-(second + 1), -(node + 1)});
            visited[node + 1] = 1;
        }
        if (node - 1 >= 0 && !visited[node - 1]) {
            pq.push({-(second + 1), -(node - 1)});
            visited[node - 1] = 1;
        }

    }
    cout << answer;
}