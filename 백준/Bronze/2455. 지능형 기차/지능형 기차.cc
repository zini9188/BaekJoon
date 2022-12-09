#include <iostream>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int getOn, getOff;
    int sum = 0, answer = 0;

    for (int i = 0; i < 4; i++) {
        cin >> getOff >> getOn;
        sum -= getOff;
        sum += getOn;
        answer = max(answer, sum);
    }
    cout << answer;
}