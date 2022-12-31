#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int N, k;
    vector<int> arr(1001);
    cin >> N >> k;
    for (int i = 0; i < N; i++) {
        cin >> arr[i];
    }

    sort(arr.begin(), arr.begin() + N);
    cout << arr[N - k];
}