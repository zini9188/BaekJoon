#include <iostream>
#include <vector>

using namespace std;

int main() {
    int number;
    int max = -1;
    int x, y;

    for (int row = 0; row < 9; row++) {
        for (int col = 0; col < 9; col++) {
            cin >> number;
            if (number > max) {
                max = number;
                x = row;
                y = col;
            }
        }
    }

    cout << max << endl;
    cout << x + 1 << " " << y + 1;
}