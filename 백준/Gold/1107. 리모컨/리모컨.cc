#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

vector<bool> broken(10);

bool check(int n) {
    if(n == 0){
        return broken[0];
    }
    while (n) {
        if (broken[n % 10]) return true;
        n /= 10;
    }
    return false;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int channel, m, x;
    cin >> channel >> m;
    for (int i = 0; i < m; i++) {
        cin >> x;
        broken[x] = true;
    }
    int answer = abs(channel - 100);
    for (int i = 0; i < 1000000; i++) {
        if (!check(i)) {
            int cnt = to_string(i).size() + abs(channel - i);
            answer = min(cnt, answer);
        }
    }
    cout << answer;
}