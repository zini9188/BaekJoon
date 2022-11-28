#include <iostream>
#include <map>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    /*
     * 포켓몬의 개수 N, 내가 맞춰야 하는 문제의 개수 M
     */
    int N, M;
    cin >> N >> M;
    /*
     * N개의 줄에 포켓몬의 번호가 1번인 포켓몬부터 N번에 해당하는 포켓몬까지 한 줄에 하나씩 입력
     */
    string name, question;
    map<string, int> dictionaryByString;
    map<int, string> dictionaryByInt;
    for (int i = 1; i <= N; i++) {
        cin >> name;
        dictionaryByString[name] = i;
        dictionaryByInt[i] = name;
    }
    /*
     * M개의 줄에 각각의 문제에 대한 답 출력
     * 숫자의 경우 해당하는 포켓몬의 이름
     * 문자의 경우 해당하는 번호
     */
    for (int i = 0; i < M; i++) {
        cin >> question;
        if (isdigit(question[0])) {
            cout << dictionaryByInt[stoi(question)] << "\n";
        } else {
            cout << dictionaryByString[question] << "\n";
        }
    }
}