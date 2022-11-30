#include <iostream>
#include <vector>
#include <map>
#include <queue>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    /*
     *  n의 크기
     */
    int n;
    cin >> n;
    vector<int> way(1001);
    way[1] = 1;
    way[2] = 2;
    way[3] = 3;
    /*
     *  동적계획법 사용
     */
    for (int i = 4; i <= n; i++) {
        way[i] = (way[i - 2] + way[i - 1]) % 10007;
    }
    cout << way[n];
}