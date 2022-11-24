#include <cmath>
#include <iostream>
using namespace std;

int search(int card[], int N, int M) {
    int sum;
    int max = 0;
    // 0,1,2 .. 0,2,3 .. 0,3,4 ... 순으로 합을 구하여 M과 비교한다.
    for (int i = 0; i < N - 2; i++) {
        for (int j = i + 1; j < N - 1; j++) {
            for (int k = j + 1; k < N; k++) {
                sum = 0;
                // 3개의 카드 합을 구한다.
                sum += card[i] + card[j] + card[k];

                // 합이 M과 같으면 즉시 종료.
                if (sum == M)
                    return sum;

                // M보다 작고 이전 합보다 큰 경우 max의 값을 교체한다.
                if (sum < M && sum > max)
                    max = sum;
            }
        }
    }
    return max;
}

int main() {
    // 1. 첫째 줄에 카드 개수 N과, M 이 주어짐
    int M, N;
    cin >> N >> M;
    // 2. 둘째 줄에는 카드에 쓰여 있는 수가 주어짐.
    int card[N];
    for (int i = 0; i < N; i++)
        cin >> card[i];

    cout << search(card, N, M);
}