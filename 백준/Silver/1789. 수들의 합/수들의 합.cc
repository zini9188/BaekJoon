#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <map>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    long long S, N = 1, answer = 0;

    cin >> S;

    while (true) {
        if (S - N >= 0) {
            S -= N;
            answer++;
        } else if (S - N < 0) break;
        
        N++;
    }

    cout << answer;
}