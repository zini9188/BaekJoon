#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

#define M 1234567891
#define R 31

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int n;
    string s;
    cin >> n >> s;

    long long ans = 0;
    long long r = 1;
    for (char i : s) {
        ans = (ans + (i - 'a' + 1) * r) % M;
        r = (r * R) % M;
    }
    cout << ans;
}