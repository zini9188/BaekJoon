#include <iostream>
using namespace std;
string A, B;

int main() {
    cin >> A >> B;
    int ans = 51;
    for (int i = 0; i <= B.length() - A.length(); i++) {
        int cnt = 0;
        for (int j = 0; j < A.length(); j++) {
            if (B[j + i] != A[j])
                cnt++;
        }
        ans = min(cnt, ans);
    }
    cout << ans;
}