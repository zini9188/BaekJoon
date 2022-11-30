#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
vector<pair<int,int>> points;

void input() {
    int N;
    cin >> N;
    int x, y;
    for (int i = 0; i < N; i++) {
        cin >> x >> y;
        points.emplace_back(x,y);
    }
}

void solution() {
    sort(points.begin(), points.end());
    for(auto point : points){
        cout << point.first << " " << point.second << "\n";
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    input();
    solution();
}