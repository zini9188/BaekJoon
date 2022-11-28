#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <map>

using namespace std;

map<int, int> card;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int N, M;
    cin >> N;
    int n;
    for (int i = 0; i < N; i++) {
        cin >> n;
        card[n]++;
    }

    cin >> M;
    for (int i = 0; i < M; i++) {
        cin >> n;
        cout << card[n] << " ";
    }
}