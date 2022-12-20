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

    for(int i = 0; i < n; i++){
        for(int k = 0; k < i; k++) cout << " ";
        for(int j = 0; j < n - i; j++) cout << "*";
        for(int l = 0; l < n - i - 1; l ++) cout << "*";
        cout << endl;
    }

}
