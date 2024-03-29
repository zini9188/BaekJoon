#include <algorithm>
#include <iostream>
using namespace std;

int rgb[1001][3];

int n;
int RGB() {
    for (int i = 1; i < n; i++) {
        rgb[i][0] += min(rgb[i - 1][1], rgb[i - 1][2]);
        rgb[i][1] += min(rgb[i - 1][0], rgb[i - 1][2]);
        rgb[i][2] += min(rgb[i - 1][1], rgb[i - 1][0]);
    }

    return min({rgb[n - 1][0], rgb[n - 1][1], rgb[n - 1][2]});
}

int main() {
    cin >> n;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < 3; j++) {
            cin >> rgb[i][j];
        }
    }

    cout << RGB();
}