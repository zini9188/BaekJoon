#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int A[100001];
int N;

int binarySearch(int target) {
    int left = 0, right = N - 1, mid;

    while (left <= right) {
        mid = (left + right) / 2;
        if (A[mid] == target) {
            return 1;
        } else if (A[mid] > target) {
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }
    return 0;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int x,M;
    cin >> N;

    for (int i = 0; i < N; i++) {
        cin >> x;
        A[i] = x;
    }
    sort(A, A + N);

    cin >> M;
    for (int i = 0; i < M; i++) {
        cin >> x;
        cout << binarySearch(x) << "\n";
    }
}