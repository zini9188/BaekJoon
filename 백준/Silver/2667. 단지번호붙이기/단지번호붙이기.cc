#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;
int answer = 0;
int N;
vector<int> X = {1, -1, 0, 0};
vector<int> Y = {0, 0, 1, -1};

// 시작 지점부터 연결되어 있는 부분을 전부 0으로 변경해준다.
int dfs(int x, int y, vector<vector<int>> &map) {
    map[x][y] = 0;
    answer++;

    for (int i = 0; i < 4; i++) {
        int nextX = x + X[i];
        int nextY = y + Y[i];
        if (nextX >= 0 && nextX < N && nextY >= 0 && nextY < N && map[nextX][nextY] == 1) {
            dfs(nextX, nextY, map);
        }
    }

    return answer;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);


    cin >> N;
    string line;
    vector<vector<int>> map(N, vector<int>(N));
    for (int i = 0; i < N; i++) {
        cin >> line;
        for (int j = 0; j < line.size(); j++) {
            map[i][j] = line[j] - '0';
        }
    }

    vector<int> result;
    int count = 0;
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if (map[i][j] == 1) {
                result.push_back(dfs(i, j, map));
                answer = 0;
                count ++;
            }
        }
    }
    sort(result.begin(),result.end());
    cout << count << "\n";
    for(auto r : result){
        cout << r << "\n";
    }

}