#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;
vector<int> S(100001);
vector<int> V(100001);
vector<int> temp;
int N, M;

void print() {
    for (int i = 0; i < temp.size(); i++) {
        cout << temp[i] << " ";
    }
    cout << "\n";
}

void dfs(int count) {
    if (count == M) {
        print();
        return;
    }

    for (int i = 0; i < N; i++) {
        if (!V[i]) {
            V[i] = 1;
            temp.push_back(S[i]);
            dfs(count + 1);
            temp.pop_back();
            V[i] = 0;
        }
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    cin >> N >> M;
    for (int i = 0; i < N; i++) {
        cin >> S[i];
    }
    sort(S.begin(), S.begin() + N);
    dfs(0);
}