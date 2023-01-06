#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int T;
    cin >> T;

    while (T--) {
        int N;
        vector<pair<int, int>> arr(100001, {0, 0});
        cin >> N;

        for (int i = 0; i < N; i++) cin >> arr[i].first >> arr[i].second;

        sort(arr.begin(), arr.begin() + N);

        int index = 0;
        int ans = 1;
        for (int i = 1; i < N; i++) {
            if (arr[index].second > arr[i].second) {
                ans++;
                index = i;
            }
        }
        cout << ans << '\n';
    }
}