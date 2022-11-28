#include <iostream>
#include <map>
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
    vector<bool> visited(100001);
    priority_queue<pair<int, int>, vector<pair<int,int>>, greater<>> pq;
    pq.push(make_pair(0, N));
    visited[N] = true;
    while (!pq.empty()) {
        int second = pq.top().first;
        int position = pq.top().second;
        pq.pop();

        if (position == K) {
            answer = min(second, answer);
        }
        if (position * 2 < MAX_SIZE && !visited[position * 2]) {
            pq.push(make_pair(second, position * 2));
            visited[position * 2] = true;
        }
        if (position + 1 < MAX_SIZE && !visited[position + 1]) {
            pq.push(make_pair(second + 1, position + 1));
            visited[position + 1] = true;
        }
        if (position - 1 >= 0 && !visited[position - 1]) {
            pq.push(make_pair(second + 1, position - 1));
            visited[position - 1] = true;
        }
    }

    cout << answer;
}