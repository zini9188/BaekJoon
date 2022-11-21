#include <iostream>
using namespace std;
int N, M;
int square[51][51];
int fir, sec, thd, four;
int size;
int resultSize;
string input;

void check(int curSize) {
    if (curSize == min(N, M)) {
        return;
    }
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < M; j++) {
            if (i + curSize >= N || j + curSize >= M)
                continue;
            fir = square[i][j];
            sec = square[i][j + curSize];
            thd = square[i + curSize][j];
            four = square[i + curSize][j + curSize];
            if (fir == sec && sec == thd && thd == four) {
                resultSize = max(curSize + 1, resultSize);
            }
            fir = sec = thd = four = 0;
        }
    }
    check(curSize + 1);
}

int main() {
    cin >> N >> M;
    for (int i = 0; i < N; i++) {
        cin >> input;
        for (int j = 0; j < M; j++) {
            square[i][j] = input[j] - '0';
        }
    }
    check(0);
    cout << resultSize * resultSize;
}