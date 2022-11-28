#include <string>
#include <vector>
#include <iostream>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    vector<int> students(31);
    int n;
    for (int i = 1; i <= 28; i++) {
        cin >> n;
        students[n] = 1;
    }

    for(int i = 1; i <= 30; i++){
        if(!students[i]){
            cout << i << "\n";
        }
    }
}