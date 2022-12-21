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
    vector<long long> points;
    vector<long long> temp;
    int n;
    for (int i = 0; i < N; i++) {
        cin >> n;
        points.push_back(n);
        temp.push_back(n);
    }
    sort(temp.begin(), temp.begin() + N);
    temp.erase(unique(temp.begin(), temp.end()), temp.end());
    for (auto point: points) {
        cout << lower_bound(temp.begin(), temp.end(), point) - temp.begin() << " ";
    }
}
