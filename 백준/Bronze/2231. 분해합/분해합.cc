#include <cmath>
#include <iostream>
using namespace std;
int search(int n) {
    int temp, sum, min = 999999, result, i = 1;

    while (i < n) {  // i = 1 ~ n - 1까지
        temp = n - i;
        result = temp;
        sum = temp;
        while (temp > 0) {  // temp의 각 자리수를 더한다.
            sum += temp % 10;
            temp /= 10;
        }
        if (sum == n && result < min)  // sum이 n과 같으면서 생성자가 최소값인 경우를 구한다.
            min = result;
        i++;
    }
    if (min == 999999)
        return 0;
    return min;
}

int main() {
    // 1. N을 입력받는다.
    int N;
    cin >> N;
    // 2. N의 분해합은 N과 N을 이루는 각 자리수의 합
    // 245의 분해합은 256(245 + 2 + 4 + 5)이다.
    cout << search(N);
}