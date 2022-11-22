#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    int N, M;
    cin >> N >> M;

    vector<int> arr(N + 1);
    vector<int> sum(N + 1);

    for (int index = 1; index <= N; index++) {
        cin >> arr[index];
        sum[index] = arr[index] + sum[index - 1];
    }

    int i, j;
    for (int index = 0; index < M; index++) {
        cin >> i >> j;
        cout << sum[j] - sum[i - 1] << "\n";
    }
}