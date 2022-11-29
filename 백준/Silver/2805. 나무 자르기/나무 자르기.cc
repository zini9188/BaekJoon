#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int N, M, answer = 0;
    cin >> N >> M;
    vector<int> woods(1000001);
    for (int i = 0; i < N; i++) {
        cin >> woods[i];
    }
    sort(woods.begin(), woods.begin() + N);
    int left = 1, right = woods[N - 1], mid;
    while (left <= right) {
        long long mod = 0;
        mid = (left + right) / 2;
        for (int i = 0; i < N; i++) {
            if (woods[i] > mid) mod += woods[i] - mid;
        }
        if (mod >= M) {
            answer = max(answer, mid);
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    cout << answer;

}