#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

#define INF 987654321

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    int n, m;
    int city[101][101];
    cin >> n >> m;

    for(int i = 1; i <= n; i++){
        for(int j = 1; j <= n; j++){
            city[i][j] = INF;
        }
    }
    int a, b, c;
    for (int i = 0; i < m; i++) {
        cin >> a >> b >> c;
        if(city[a][b] > c)
            city[a][b] = c;
    }

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            for (int k = 1; k <= n; k++) {
                city[j][k] = min(city[j][k], city[j][i] + city[i][k]);
            }
        }
    }

    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            if (i == j || city[i][j] == INF){
                cout << 0;
            }else{
                cout << city[i][j];
            }
            cout << " ";
        }cout << '\n';
    }

}