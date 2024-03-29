#include <iostream>
using namespace std;
int l, arr[51], n, st = 1, ed, cnt;

void quickSort(int s, int e) {
    if (s >= e)
        return;
    int p = s;
    int i = p + 1;
    int j = e;
    int temp;
    while (i <= j) {
        while (i <= e && arr[i] <= arr[p]) {
            i++;
        }
        while (j > s && arr[j] >= arr[p]) {
            j--;
        }
        if (i > j) {
            temp = arr[j];
            arr[j] = arr[p];
            arr[p] = temp;
        } else {
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
    quickSort(s, j - 1);
    quickSort(j + 1, e);
}
void check() {
    int ck = 0;
    for (int i = 0; i < l; i++) {
        if (!ck && n < arr[i]) {
            ed = arr[i] - 1;
            ck = 1;
        }
        if (n > arr[i])
            st = arr[i] + 1;
        if (n == arr[i]) {
            cout << "0";
            exit(0);
        }
    }
}
int main() {
    cin >> l;
    for (int i = 0; i < l; i++)
        cin >> arr[i];

    quickSort(0, l - 1);
    cin >> n;
    check();

    cout << (n - st) * (ed - n + 1) + (ed - n);
}