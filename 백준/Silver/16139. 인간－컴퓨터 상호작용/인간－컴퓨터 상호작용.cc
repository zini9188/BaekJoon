#include <iostream>
#include <vector>

using namespace std;

int sum[200001][26];

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int q;
    string S;
    cin >> S;

    sum[0][S[0] - 'a'] = 1;
    for (int i = 1; i < S.size(); i++) {
        for (int j = 0; j < 26; j++) {
            sum[i][j] += sum[i - 1][j];
        }
        sum[i][S[i] - 'a']++;
    }
    
    cin >> q;
    char alphabet;
    int l, r;
    for (int i = 0; i < q; i++) {
        cin >> alphabet >> l >> r;
        if (l == 0) {
            cout << sum[r][alphabet - 'a'] << "\n";
        } else {
            cout << sum[r][alphabet - 'a'] - sum[l - 1][alphabet - 'a'] << "\n";
        }
    }
}