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

    int M;
    cin >> M;
    map<int, bool> S;
    string cmd;
    int x;
    for (int i = 0; i < M; i++) {
        cin >> cmd;
        if (cmd == "add") {
            cin >> x;
            S[x] = true;
        } else if (cmd == "remove") {
            cin >> x;
            S[x] = false;
        } else if (cmd == "check") {
            cin >> x;
            if (S[x]) cout << "1\n";
            else if (!S[x])cout << "0\n";
        } else if (cmd == "toggle") {
            cin >> x;
            if (S[x]) S[x] = false;
            else if (!S[x]) S[x] = true;
        } else if (cmd == "all") {
            for (int j = 1; j <= 20; j++) {
                S[j] = true;
            }
        } else if (cmd == "empty") {
            for (int j = 1; j <= 20; j++) {
                S[j] = false;
            }
        }
    }
}