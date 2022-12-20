#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int n;
    cin >> n;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j <= i; j++) cout << "*";
        for (int k = 0; k < (n - i - 1) * 2; k++) cout << " ";
        for (int l = 0; l <= i; l++) cout << "*";
        cout << endl;
    }

    for (int i = n - 1; i > 0; i--) {
        for (int j = 0; j < i; j++) cout << "*";
        for (int k = 0; k <= (n - i ) * 2 - 1; k++) cout << " ";
        for (int l = 0; l < i; l++) cout << "*";
        cout << endl;
    }

}
