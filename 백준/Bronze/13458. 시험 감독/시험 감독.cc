#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    int N, A, B, C;
    cin >> N;
    vector<int> a;
    for (int i = 0; i < N; i++) {
        cin >> A;
        a.push_back(A);
    }
    cin >> B >> C;
    long long answer = 0;
    for (int i = 0; i < N; i++) {
        long long  need = a[i] - B;
        if (need <= 0) answer++;
        else if (need % C == 0) answer += need / C + 1;
        else answer += need / C + 2;
    }
    cout << answer;
}
