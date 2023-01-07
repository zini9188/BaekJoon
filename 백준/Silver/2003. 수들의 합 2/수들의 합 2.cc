#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int n, m;
    cin >> n >> m;
    int A[10001];
    for (int i = 0; i < n; i++) cin >> A[i];

    int left = 0, right = 0, sum = A[0];
    int ans = 0;
    while (right < n && left <= right) {
        if (sum == m) {
            sum += A[++right];
            ans++;
        } else if (sum > m) {
            sum -= A[left++];
            if (left > right) {
                right = left;
                sum = A[left];
            }
        } else {
            sum += A[++right];
        }
    }
    cout << ans;
}