#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int L, C;
vector<string> words(16);
vector<bool> visited(16);
string aeiou = "aeiou";
vector<string> answers;

bool checkPossible(const string &word) {
    int vowel = 0, consonant = 0;
    for (auto w: word) {
        if (find(aeiou.begin(), aeiou.end(), w) != aeiou.end()) vowel++;
        else consonant++;
    }

    if (vowel >= 1 && consonant >= 2) return true;
    else return false;
}

void dfs(int length, const string &word) {
    if (length == L && checkPossible(word)) {
        answers.push_back(word);
        return;
    }

    for (int i = 0; i < C; i++) {
        if (!visited[i] && word.back() < words[i][0]) {
            visited[i] = true;
            dfs(word.length() + 1, word + words[i]);
            visited[i] = false;
        }
    }
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    cin >> L >> C;
    for (int i = 0; i < C; i++) {
        cin >> words[i];
    }

    for (int i = 0; i < C; i++) {
        visited[i] = true;
        dfs(1, words[i]);
        visited[i] = false;
    }

    sort(answers.begin(), answers.end());

    for (const auto &answer: answers) {
        cout << answer << "\n";
    }
}