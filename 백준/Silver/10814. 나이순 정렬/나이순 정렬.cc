#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int comp(const pair<int, string>& a, const pair<int, string>& b) {
    return a.first < b.first;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int N;
    cin >> N;
    vector<pair<int, string>> customer(100001);
    for (int i = 0; i < N; i++) {
        cin >> customer[i].first >> customer[i].second;
    }
    
    // 같은 값이면 그래도 두는 함수
    stable_sort(customer.begin(), customer.begin() + N, comp);


    for(int i = 0; i < N; i++){
        cout << customer[i].first << " " << customer[i].second << "\n";
    }
}
