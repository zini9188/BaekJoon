#include <iostream>
using namespace std;
int n, kim, lim;
int round;
bool match = false;

bool check(int a, int b) {
    if (a % 2 == 1 && a + 1 == b) {  // a가 홀수이며, a+1이 b인 경우
        return true;
    }
    return false;
}
int main() {
    // n명, 김지민, 임한수 입력
    cin >> n >> kim >> lim;

    while (!match) {
        if (kim > lim) {
            match = check(lim, kim);
        } else {
            match = check(kim, lim);
        }

        round++;
        kim = kim % 2 == 0 ? kim / 2 : kim / 2 + 1;
        lim = lim % 2 == 0 ? lim / 2 : lim / 2 + 1;
    }
    cout << round;
}
