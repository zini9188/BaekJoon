#include <iostream>
#include <vector>
#include <map>
#include <queue>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    /*
     * N: 컴퓨터의 수 (N <= 100)
     * M: 직접 연결되어 있는 컴퓨터 쌍의 수
     */
    int N, M, answer = 0;
    cin >> N >> M;
    map<int, vector<int>> connection;
    vector<int> visited(101);
    int left, right;
    for (int i = 0; i < M; i++) {
        cin >> left >> right;
        connection[left].push_back(right);
        connection[right].push_back(left);
    }
    queue<int> q;
    q.push(1);
    visited[1] = 1;

    while (!q.empty()) {
        int node = q.front();
        q.pop();

        for (int nextNode: connection[node]) {
            if (!visited[nextNode]) {
                q.push(nextNode);
                visited[nextNode] = 1;
                answer++;
            }
        }
    }
    cout << answer;
}