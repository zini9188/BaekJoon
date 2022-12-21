#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);


    for (int i = 0; i < 3; i++) {
        int a = 0, c;
        for (int j = 0; j < 4; j++) {
            cin >> c;
            if (c == 0) a++;
        }
        if (a == 1) cout << "A\n";
        else if (a == 2) cout << "B\n";
        else if (a == 3) cout << "C\n";
        else if (a == 4) cout << "D\n";
        else cout << "E\n";        
    }
}
