
#include <iostream>

using namespace std;
int main() {
    string input, temp;
    bool isSub = false;
    int sum = 0;

    cin >> input;

    for (int i = 0; i <= input.length(); i++) {
        temp.clear();
        while (i < input.length() && input[i] != '+' && input[i] != '-') {
            temp += input[i++];
        }

        if (isSub) {
            sum -= stoi(temp);
        } else {
            sum += stoi(temp);
        }

        if (!isSub && input[i] == '-')
            isSub = true;
    }
    cout << sum;
}