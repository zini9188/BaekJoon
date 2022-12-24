#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <valarray>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int T, a, b;
    cin >> T;
    while (T--) {
        cin >> a >> b;
        a %= 10;
        if (a == 0) cout << "10\n";
        else {
            b %= 4;
            if (b == 0) b = 4;
            cout << (int)pow(a, b) % 10 << '\n';
        }
    }
}