#include <iostream>
using namespace std;

int stat[21][21];
int n;
bool isLink[21];
int myMin = 1000000;

void dfs(int x, int idx) {
    if (x == n / 2) {
        int start = 0, link = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (isLink[i] && isLink[j])
                    link += stat[i][j];
                if (!isLink[i] && !isLink[j])
                    start += stat[i][j];
            }
        }

        int temp = abs(start - link);
        myMin = myMin > temp ? temp : myMin;
        return;
    }

    for (int i = idx; i < n; i++) {
        isLink[i] = true;
        dfs(x + 1, i + 1);
        isLink[i] = false;
    }
}

int main() {
    cin >> n;
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            cin >> stat[i][j];
        }
    }

    dfs(0, 1);

    cout << myMin;
}