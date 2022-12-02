#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int L, C;
vector<string> words(16);
string aeiou = "aeiou";
vector<string> answers;

bool checkCondition(const string &word) {
    int vowel = 0, consonant = 0;
    for (auto w: word) {
        if (find(aeiou.begin(), aeiou.end(), w) != aeiou.end()) vowel++;
        else consonant++;
    }

    if (vowel >= 1 && consonant >= 2) return true;
    else return false;
}

void dfs(int length, const string &word) {
    if (length == L && checkCondition(word)) {
        answers.push_back(word);
        return;
    }

    for (int i = 0; i < C; i++) {
        if (word.back() < words[i][0]) dfs(word.length() + 1, word + words[i]);
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> L >> C;
    for (int i = 0; i < C; i++) cin >> words[i];

    for (int i = 0; i < C; i++) dfs(1, words[i]);

    sort(answers.begin(), answers.end());

    for (const auto &answer: answers) cout << answer << "\n";

}