#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

vector<int> cable(500001);

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    /*
     * 랜선의 개수 K, 필요한 랜선의 개수 N
     */
    int N, K, answer = 0;
    cin >> K >> N;
    for (int i = 0; i < K; i++) {
        cin >> cable[i];
    }
    sort(cable.begin(), cable.begin() + K);

    /*
     * left는 1 right는 가장 긴 케이블
     */
    long long left = 1, right = cable[K - 1], mid;
    while (left <= right) {
        mid = (left + right) / 2;
        int count = 0;
        for (int i = 0; i < K; i++) {
            count += cable[i] / (int)mid;
        }
        /*
         * 갯수가 N보다 크면 left의 값을 올려 나누는 값을 크게 만들어 갯수를 줄여준다.
         */
        if (count >= N) {
            answer = max(answer, (int)mid);
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    cout << answer;
}


