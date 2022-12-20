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
    for (int i = n - 1; i > 0; i--) {
        for (int j = 0; j < n - i; j++) cout << "*";
        cout << endl;
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n - i; j++) cout << "*";
        cout << endl;
    }
}