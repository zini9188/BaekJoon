#include <iostream>

using namespace std;

int dp[50];

int fib1(int n) {
    if (n == 1 || n == 2) {
        return 1;
    } else {
        return (fib1(n - 1) + fib1(n - 2));
    }
}

int fib2(int n) {
    int count = 0;
    dp[1] = dp[2] = 1;
    for (int i = 3; i <= n; i++) {
        dp[i] = dp[i - 1] + dp[i - 2];
        count ++;
    }
    return count;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int n;
    cin >> n;

    cout << fib1(n) << endl;
    cout << fib2(n);
}