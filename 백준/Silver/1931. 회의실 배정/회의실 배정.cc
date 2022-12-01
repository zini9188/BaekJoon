#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

/*
 * N: 회의의 수
 * (1 <= N <= 100,000)
 * 시작 시간과 끝나는 시간
 * (2 <= start, end <= N + 1)
 */
int N, answer = 0;
vector<pair<int, int>> rooms(100001);

/*
 * 회의 종료 시간이 빠른 순으로 정렬
 * 같으면 시작 시간이 빠른 순으로 정렬
 */
int comp(pair<int, int> a, pair<int, int> b) {
    if(a.second == b.second) return a.first < b.first;
    return a.second < b.second;
}

/*
 * 입력 기능
 */
void input() {
    cin >> N;
    for (int i = 0; i < N; i++) {
        cin >> rooms[i].first >> rooms[i].second;
    }
}

/*
 * 빨리 끝나는 것부터 집어 넣기
 */
void solution(){
    sort(rooms.begin(), rooms.begin() + N, comp);
    int last = 0;
    for (int i = 0; i < N; i++) {
        if (last <= rooms[i].first) {
            last = rooms[i].second;
            answer++;
        }
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    input();
    solution();
    cout << answer;
}
