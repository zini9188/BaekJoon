#include <string>
#include <iostream>
#include <vector>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    vector<int> moneys = {500, 100, 50, 10, 5, 1};

    int N;
    cin >> N;
    int change = 1000 - N;
    int ans = 0;
    for (int money: moneys) {
        if (change >= money) {
            ans += change / money;
            change %= money;
        }
    }
    cout << ans;
}