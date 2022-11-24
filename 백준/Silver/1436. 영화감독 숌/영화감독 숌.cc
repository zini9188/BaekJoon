#include <cmath>
#include <iostream>
using namespace std;


int comp(int n) {
    string six = "666";
    string temp = to_string(n);
    int len = temp.length();

    for (int i = 0; i <= len - 3; i++)  // i ~ i + 3번째가 666이면 1을 아니면 0을 리턴
        if (temp.compare(i, 3, six) == 0)
            return 1;

    return 0;
}

int title(int n) {
    int s = 1660;
    if (n == 1)
        return 666;

    while (n > 1) {  // n의 개수를 줄여가며 숫자를 ++
        if (comp(++s) == 1)
            n--;
    }

    return s;
}

int main() {
    // 1. 첫째 줄에 숫자 N이 주어진다.
    int N;
    cin >> N;
    // 2. N번째 제목을 출력한다.
    cout << title(N);
}