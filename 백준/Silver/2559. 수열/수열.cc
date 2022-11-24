#include <iostream>
#include <vector>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int N, K;
    cin >> N >> K;
    vector<int> temperature(N);
    for (int i = 0; i < N; i++) {
        cin >> temperature[i];
    }
    int sum = 0;
    for (int i = 0; i < K; i++) {
        sum += temperature[i];
    }
    int left = 0, right = K;
    int answer = -987654321;
    while (right <= N) {
        answer = max(answer, sum);
        sum -= temperature[left++];
        sum += temperature[right++];
    }
    cout << answer;
}