// 백준 1105 팔
#include <iostream>
using namespace std;
string L, R;
int cnt;
int main() {
    cin >> L >> R;
    
    // 둘의 자리수가 다르다는 것은 10, 100, 1000과 같은 8이 아예 없는 자연수가 존재한다는 것을 의미한다.
    // 따라서 둘의 자리수가 같아야 한다.
    if (L.length() == R.length()) {
        // 둘의 값이 다르다면 반복문을 탈출한다. 
        // 최솟값을 구하는 것이기 때문에 8이 없으면 카운트가 증가될 이유가 없다.
        for (int i = 0; i < L.length(); i++) {
            if (L[i] != R[i]) {
                break;
            }
            if (L[i] == R[i] && L[i] == '8') {
                cnt++;
            }
        }
    }

    cout << cnt;
}
