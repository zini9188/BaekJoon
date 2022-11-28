#include <cmath>
#include <iostream>
using namespace std;

int main() {
    int n, cnt = 0;
    cin >> n;

    while (n >= 5) {
        cnt += n / 5;
        n /= 5;
    }
    cout << cnt;
}