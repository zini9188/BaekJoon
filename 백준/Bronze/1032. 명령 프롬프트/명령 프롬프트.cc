#include <iostream>
using namespace std;

int N;
string file;
string comp;
int main() {
    cin >> N;

    for (int i = 0; i < N; i++) {
        cin >> file;
        if (i == 0) {
            comp = file;
        } else {
            for (int j = 0; j < comp.length(); j++) {
                if (comp[j] == file[j])
                    comp[j] = file[j];
                else {
                    comp[j] = '?';
                }
            }
        }
    }
    cout << comp;
}