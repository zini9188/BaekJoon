#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

string palindrome(string n) {
    int left = 0, right = n.size() - 1;
    while (left <= right) {
        if (n[left] == n[right]) {
            left++;
            right--;
        } else return "no";        
    }
    return "yes";
}
int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    string n;
    while (true) {
        cin >> n;
        if (n == "0") break;
        cout << palindrome(n) << "\n";
    }
}