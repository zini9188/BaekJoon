#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    int N;
    vector<int> arr(4000001);
    cin >> N;

    for (int i = 2; i <= N; i++)
        arr[i] = i;

    for (int i = 2; i * i <= N; i++) {
        if (arr[i]) {
            for (int j = i * i; j <= N; j += i) {
                arr[j] = 0;
            }
        }
    }
    vector<int> connect;
    for (int i = 2; i <= N; i++) {
        if (arr[i]) {
            connect.push_back(i);
        }
    }

    int left = 0, right = 0;
    int sum = 2;
    int answer = 0;
    while (right < connect.size() && left <= right) {
        if (sum == N) {
            answer++;
            sum += connect[++right];
        } else if (sum < N) {
            sum += connect[++right];
        } else {
            sum -= connect[left++];
        }
    }

    cout << answer;
}