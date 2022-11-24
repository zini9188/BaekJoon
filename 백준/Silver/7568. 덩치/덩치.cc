#include <cmath>
#include <iostream>
using namespace std;

void compare(int w[], int h[], int n) {
    int result[n] = {0};

    // 0~n까지를 0~n까지와 비교하여 작은 경우에 랭킹을 올려준다.
    for (int i = 0; i < n; i++) {
        result[i] = 1;
        for (int j = 0; j < n; j++) {
            if (w[i] < w[j] && h[i] < h[j])
                result[i]++;
        }
    }

    for (int i = 0; i < n; i++)
        cout << result[i] << " ";
}

int main() {
    // 전체 사람의 수 N
    int N;
    cin >> N;
    // N명의 몸무게, 키를 받아온다
    int weight[N], height[N];
    for (int i = 0; i < N; i++)
        cin >> weight[i] >> height[i];

    compare(weight, height, N);
}