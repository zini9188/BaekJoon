#include <iostream>
using namespace std;
int t, m, n, k;
int ground[51][51];
int mPos;
int nPos;
int bug;
void check(int x, int y) {
    ground[x][y] = 0;
    // 동
    if (y < m - 1 && ground[x][y + 1] == 1)
        check(x, y + 1);
    // 서
    if (y > 0 && ground[x][y - 1] == 1)
        check(x, y - 1);
    // 남
    if (x < n - 1 && ground[x + 1][y] == 1)
        check(x + 1, y);
    // 북
    if (x > 0 && ground[x - 1][y] == 1)
        check(x - 1, y);
}

void search() {
    for (int i = 0; i < n;i++){
        for (int j = 0; j < m;j++){
            if (ground[i][j] == 1) {
                check(i, j);
                bug++;
            }
        }
    }
       

}

int main() {
    cin >> t;

    while (t--) {
        cin >> m >> n >> k;
        bug = 0;
        while (k--) {
            cin >> mPos >> nPos;
            ground[nPos][mPos] = 1;
        }

        search();
        cout << bug << endl;
    }
}
