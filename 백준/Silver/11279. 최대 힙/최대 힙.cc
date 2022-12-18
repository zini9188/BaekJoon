#include <string>
#include <iostream>
#include <queue>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int N, M;
    cin >> N;
    priority_queue<int> pq;
    for (int i = 0; i < N; i++) {
        cin >> M;
        if (M == 0) {
            if (pq.empty()) {
                cout << "0\n";
            } else {
                cout << pq.top() << '\n';
                pq.pop();
            }
        } else {
            pq.push(M);
        }
    }
}