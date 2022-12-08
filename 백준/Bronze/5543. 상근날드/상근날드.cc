#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    vector<int> hamburger(3);
    vector<int> drink(2);

    for (int i = 0; i < 3; i++) cin >> hamburger[i];
    for (int i = 0; i < 2; i++) cin >> drink[i];
    sort(hamburger.begin(),hamburger.end());
    sort(drink.begin(),drink.end());
    cout << hamburger.front() + drink.front() - 50;

}