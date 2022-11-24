#include <iostream>
using namespace std;

// 호텔의 고객을 적어도 C명 늘이기 위해 투자해야 하는 돈의 최솟값을 구하는 프로그램
int C, N;
// 고객은 최대 1000명, 가격은 100까지 있으므로 최소 100000의 dp 배열이 필요함.
int dp[100001];
int price[21];
int people[21];
int main() {
    // 고객 C명 (C <= 1000), 홍보 가능한 도시의 수 N개 (N <= 20)
    cin >> C >> N;

    for (int i = 0; i < N; i++) {
        cin >> price[i] >> people[i];
    }
    // dp를 이용.
    // i의 값은 가격에 대한 값
    for (int i = 1; i < 100001; i++) {
        for (int j = 0; j < N; j++) {
            // 가격이 나누어 떨어지는 경우.
            if (i % price[j] == 0) {
                dp[i] = max(dp[i], (i / price[j]) * people[j]);
            }
            // i - price[j]에 해당하는 배열의 값을 변경
            if (i - price[j] >= 0) {
                dp[i] = max(dp[i], dp[i - price[j]] + people[j]);
            }
        }
        if (dp[i] >= C) {
            cout << i;
            return 0;
        }
    }
}