#include <iostream>
using namespace std;
int n, m;
// 패키지 / 낱개
int brand[51][2];
int pac, indiv;
int pacMin = 1001, indivMin = 1001;
int opt1, opt2, opt3;
int result;
int main() {
    // 기타줄의 개수, 브랜드
    cin >> n >> m;
    for (int i = 0; i < m; i++) {
        cin >> brand[i][0] >> brand[i][1];
        pacMin = brand[i][0] > pacMin ? pacMin : brand[i][0];
        indivMin = brand[i][1] > indivMin ? indivMin : brand[i][1];
    }

    pac = n / 6;
    indiv = n % 6;
    // 패키지를 필요 개수보다 많이 사며 최소값인 경우
    opt1 = (pac + 1) * pacMin;
    // 패키지와 낱개를 섞어 딱 맞게 사며 최소값인 경우
    opt2 = pac * pacMin + indiv * indivMin;
    // 낱개만 사며 최솟값인 경우
    opt3 = n * indivMin;

    result = min(min(opt1, opt2), opt3);
    cout << result;
}
