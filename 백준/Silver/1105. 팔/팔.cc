#include <iostream>
using namespace std;
string L, R;
int cnt;
int main() {
    cin >> L >> R;

    if (L.length() == R.length()) {
        for (int i = 0; i < L.length(); i++) {
            if (L[i] != R[i]) {
                break;
            }
            if (L[i] == R[i] && L[i] == '8') {
                cnt++;
            }
        }
    }

    cout << cnt;
}