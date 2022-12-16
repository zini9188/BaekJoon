#include <string>
#include <vector>
#include <iostream>
#include <map>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int n, m;
    cin >> n >> m;

    vector<string> listen(500001);
    vector<string> watch(500001);
    map<string, pair<int, int>> both;

    for (int i = 0; i < n; i++) {
        cin >> listen[i];
        both[listen[i]].first = 1;
    }

    for (int i = 0; i < m; i++) {
        cin >> watch[i];
        both[watch[i]].second = 1;
    }

    vector<string> answer;

    for (const auto &each: both) {
        if (each.second.first == 1 && each.second.second == 1) {
            answer.push_back(each.first);
        }
    }

    sort(answer.begin(), answer.end());
    cout << answer.size() << "\n";
    for (const auto &a: answer) cout << a << "\n";
}