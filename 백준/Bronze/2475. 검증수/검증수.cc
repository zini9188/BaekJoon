#include <iostream>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int n, answer = 0;
    for (int i = 0; i < 5; i++) {
        cin >> n;
        answer += n * n;
    }
    cout << answer % 10;
}