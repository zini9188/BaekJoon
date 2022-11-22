#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

/*
 * sort를 계속 사용해서 오류가 났었음
 */

int main() {
    int N, S;
    cin >> N >> S;
    vector<int> arr(N);
    for (int i = 0; i < N; i++) {
        cin >> arr[i];
    }

    int left = 0, right = 0;
    int sum = arr[left], answer = 100000000; // 처음엔 0번째 인덱스로 시작
    while (left <= right && right < N) { // 왼쪽 값이 오른쪽 값을 넘어가거나 오른쪽 값이 끝 + 1에 도달하면
        if (sum >= S) { // 합이 S보다 크다면 정답을 구해본다.
            answer = min(right - left + 1, answer);
            sum -= arr[left++];
        } else if (sum < S) { // 작다면 오른쪽을 늘려준다.
            sum += arr[++right];
        }
    }
    answer = answer == 100000000 ? 0 : answer;

    cout << answer << endl;

}