#include <math.h>

#include <iostream>

using namespace std;
double x[4], y[4];
double Line[3];

void getSubRound() {
    double M1 = 1987654321;
    double M2 = 1987654321;
    // 같은 선상에 있는 경우
    if (x[0] != x[1])
        M1 = abs(y[1] - y[0]) / abs(x[1] - x[0]);
    if (x[1] != x[2])
        M2 = abs(y[2] - y[1]) / abs(x[2] - x[1]);

    if (M1 == M2) {
        cout << "-1.0";
        return;
    }

    for (int i = 0; i < 3; i++) {
        Line[i] = sqrt(pow(abs(x[i] - x[i + 1]), 2) + pow(abs(y[i] - y[i + 1]), 2));
    }

    double mymin, mymax;
    mymin = min(min(Line[0], Line[1]), Line[2]);
    mymax = max(max(Line[0], Line[1]), Line[2]);
    cout.fixed;
    cout.precision(16);
    cout << mymax * 2 - mymin * 2;
}

int main() {
    for (int i = 0; i < 3; i++) {
        cin >> x[i] >> y[i];
    }
    x[3] = x[0];
    y[3] = y[0];
    getSubRound();
}