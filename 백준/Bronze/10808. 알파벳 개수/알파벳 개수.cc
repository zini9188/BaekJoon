#include <string>
#include <iostream>
#include <vector>
using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    vector<int> alphabet(26, 0);
    string word;
    cin >> word;
    for (char c: word) alphabet[c - 'a']++;
    for (int alpha: alphabet) cout << alpha << " ";
}