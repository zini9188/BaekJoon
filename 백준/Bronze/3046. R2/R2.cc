#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int R1, R2, S;
    cin >> R1 >> S;
    R2 = 2 * S - R1;
    cout << R2;
}