#include <cmath>
#include <iostream>
using namespace std;

// MN개의 단위 정사각형으로 나누어진 M x N 크기의 보드가 있다.
// 이 보드를 잘라 8x8크기의 체스판으로 만든다.
// 체스판은 검은색과 흰색이 번갈아서 칠해져 있다.
// 체스판을 색칠하는 경우는 맨 왼쪽 위 칸이 흰색, 또는 검은색인 경우
// 보드가 체스판처럼 칠해져 있다는 보장이 없어, 체스판을 잘라낸 후 정사각형을 다시 칠해야 한다.
// 다시 칠해야 하는 정사각형의 최소 개수를 구하는 프로그램을 작성.

int bCnt(string b[], int n, int m) {
    string black[8] = {
        {"BWBWBWBW"},
        {"WBWBWBWB"},
        {"BWBWBWBW"},
        {"WBWBWBWB"},
        {"BWBWBWBW"},
        {"WBWBWBWB"},
        {"BWBWBWBW"},
        {"WBWBWBWB"}};
    // i = n ~ n + 8까지
    // j = m ~ m + 8까지
    int cnt = 0;
    for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {  // 비교하며 다른 문자인 경우 카운트 증가
            if (b[i + n][j + m] != black[i][j])
                cnt++;
        }
    }

    return cnt;
}
int wCnt(string b[], int n, int m) {
    string black[8] = {
        {"WBWBWBWB"},
        {"BWBWBWBW"},
        {"WBWBWBWB"},
        {"BWBWBWBW"},
        {"WBWBWBWB"},
        {"BWBWBWBW"},
        {"WBWBWBWB"},
        {"BWBWBWBW"}};
    // i = n ~ n + 8까지
    // j = m ~ m + 8까지
    int cnt = 0;
    for (int i = 0; i < 8; i++) {
        for (int j = 0; j < 8; j++) {  // 비교하며 다른 문자인 경우 카운트 증가
            if (b[i + n][j + m] != black[i][j])
                cnt++;
        }
    }

    return cnt;
}
int repaint(string b[], int n, int m) {
    int min = 32;
    int bt, wt;
    for (int i = 0; i <= n - 8; i++) {
        for (int j = 0; j <= m - 8; j++) {
            bt = bCnt(b, i, j);
            wt = wCnt(b, i, j);
            if (bt > wt && wt < min)
                min = wt;
            else if (bt <= wt && bt < min)
                min = bt;
        }
    }
    return min;
}

int main() {
    // 첫째 줄에 N과 M이 주어진다.
    // 8 <= N,M <= 50
    int N, M;
    cin >> N >> M;

    // N x M 크기의 판을 입력받는다.
    string board[N];
    for (int i = 0; i < N; i++)
        cin >> board[i];

    cout << repaint(board, N, M);
}