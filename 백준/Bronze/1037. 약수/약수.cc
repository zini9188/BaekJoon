#include <iostream>
using namespace std;

int main() {
    int n;
    int arr[51];
    int mymax = -1000001, mymin = 1000001;
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> arr[i];
        mymax = arr[i] > mymax ? arr[i] : mymax;
        mymin = arr[i] < mymin ? arr[i] : mymin;
    }
    cout << mymax * mymin << endl;
}