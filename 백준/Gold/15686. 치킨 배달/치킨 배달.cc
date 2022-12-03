#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

struct Point {
    int x, y;

    Point(int x, int y) : x(x), y(y) {}
};

int N, M, answer = 987654321;
vector<Point> chickens, houses, searches;


int visited[2501];

void bfs() {
    int chickenDist = 0;

    for (auto house: houses) {
        int sum = 987654321;
        for (auto search: searches) {
            sum = min(abs(house.x - search.x) + abs(house.y - search.y), sum);
        }
        chickenDist += sum;
    }
    answer = min(chickenDist, answer);
}

void dfs(int index, int cnt) {
    if (cnt == M) {
        bfs();
        return;
    }

    for (int i = index; i < chickens.size(); i++) {
        if (!visited[i]) {
            visited[i] = 1;
            searches.emplace_back(chickens[i]);
            dfs(i, cnt + 1);
            searches.pop_back();
            visited[i] = 0;
        }
    }


}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> N >> M;
    vector<vector<int>> map(51, vector<int>(51));
    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            cin >> map[i][j];
            if (map[i][j] == 1) houses.emplace_back(i, j);
            if (map[i][j] == 2) chickens.emplace_back(i, j);
        }
    }
    dfs(0, 0);
    cout << answer;
}
