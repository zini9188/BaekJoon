#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;
int N, S, answer = 0;
vector<int> arr(21);

void recur(int num, int index) {
    if (index == N) {
        if (num == S)
            answer++;
        return;
    }

    recur(num, index + 1);
    recur(num + arr[index], index + 1);

}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> N >> S;
    for (int i = 0; i < N; i++) {
        cin >> arr[i];
    }

    recur(0, 0);
    if (S == 0)
        answer--;
    cout << answer;
}