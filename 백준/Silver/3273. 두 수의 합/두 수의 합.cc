#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    /*
     * n개의 서로 다른 양의 정수 a1, a2, ..., an으로 이루어진 수열이 있다.
     * ai의 값은 1보다 크거나 같고, 1000000보다 작거나 같은 자연수이다. 자연수 x가 주어졌을 때,
     * ai + aj = x (1 ≤ i < j ≤ n)을 만족하는 (ai, aj)쌍의 수를 구하는 프로그램을 작성하시오.
     */
    int answer = 0;
    int n; // 수열의 크기
    cin >> n; // 수열의 크기 입력
    vector<int> arr(n); // n개의 서로 다른 양의 정수

    for (int i = 0; i < n; i++) {
        cin >> arr[i]; // n개의 서로 다른 양의 정수 입력
    }
    int x;
    cin >> x;

    int left = 0;
    int right = n - 1;
    int sum;
    
    sort(arr.begin(), arr.end()); // 배열을 정렬
    while (right > left) { // 오른쪽 인덱스가 왼쪽 인덱스보다 클때만 진행
        sum = arr[left] + arr[right]; // 두 수의 합을 구한다.
        if(sum == x){ // 정답이면 왼쪽 오른쪽 전부 변동시켜준다.
            answer++;
            left++;
            right--;
        }
        else if (sum > x) { // 합이 더 큰 값이면 오른쪽 값을 왼쪽으로 옮겨준다.
            right--;
        } else { // 합이 더 작은 값이면 왼쪽 값을 오른쪽으로 옮겨준다.
            left++;
        }
    }
    cout << answer;
}
