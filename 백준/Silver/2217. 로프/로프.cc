#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;


int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int N;
    cin >> N;
    vector<int> rope(100001);
    for (int i = 0; i < N; i++) cin >> rope[i];
    sort(rope.begin(), rope.begin() + N);
    int result = 0;
    for (int i = 0; i < N; i++) result = max(result, rope[i] * (N - i));
    cout << result;
}
