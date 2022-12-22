#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <deque>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);

    string str;
    cin >> str;
    int lines;
    char newChar;
    char command;
    deque<char> front, back;
    for(auto c : str) front.push_back(c);
    cin >> lines;
    for (int i = 0; i < lines; i++) {
        cin >> command;
        if (command == 'P') {
            cin >> newChar;
            front.push_back(newChar);
        } else if (command == 'L') {
            if(!front.empty()) {
                back.push_front(front.back());
                front.pop_back();
            }
        } else if (command == 'D') {
            if(!back.empty()){
                front.push_back(back.front());
                back.pop_front();
            }
        } else if (command == 'B') {
            if(!front.empty()){
                front.pop_back();
            }
        }
    }
    while(!front.empty()) {
        cout << front.front();
        front.pop_front();
    }
    while(!back.empty()){
        cout << back.front();
        back.pop_front();
    }

}
