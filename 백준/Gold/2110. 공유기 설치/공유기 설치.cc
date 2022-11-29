#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    vector<int> share(200001);
    int N, C;
    cin >> N >> C;
    for (int i = 0; i < N; i++) {
        cin >> share[i];
    }
    sort(share.begin(), share.begin() + N);

    int answer = 0, left = 1, right = share[N - 1], mid, mount;
    while (left <= right) {
        mid = (left + right) / 2;
        mount = share[0];
        int count = 1;
        /*
         *  mid 이상 떨어진 공유기를 설치하는 과정
         */
        for (int i = 1; i < N; i++) {
            if (share[i] - mount >= mid) {
                mount = share[i];
                count++;
            }
        }
        if (count >= C) {
            answer = max(answer, mid);
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    cout << answer;
}
