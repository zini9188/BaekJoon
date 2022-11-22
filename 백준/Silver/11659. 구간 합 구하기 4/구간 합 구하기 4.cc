#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    /*
     *  입출력 속도를 향상시키는 함수들
     *  flush를 덜하기에 입출력이 향상
     */
    ios::sync_with_stdio(false); 
    cin.tie(nullptr);
    
    int N, M;
    cin >> N >> M;

    vector<int> arr(N + 1);
    vector<int> sum(N + 1);

    /*
     * 미리 모든 합을 구해놓고 구간 입력이 들어오면 두 차이를 구해준다.
     */
    for (int index = 1; index <= N; index++) {
        cin >> arr[index];
        sum[index] = arr[index] + sum[index - 1];
    }

    int i, j;
    for (int index = 0; index < M; index++) {
        cin >> i >> j;
        cout << sum[j] - sum[i - 1] << "\n";
    }
}
