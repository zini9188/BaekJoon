#include <deque>
#include <iostream>
using namespace std;

// AC라는 새로운 언어
// 함수 R은 뒤집기(배열에 있는 수의 순서를 뒤집음) , D는 버리기(첫 번째 수를 버리는 함수)
// 함수는 조합하여 한 번에 사용할 수 있다.
// AB는 A를 수행한 다음에 바로 이어서 B를 수행하는 함수이다.
// RDD는 배열을 뒤집은 다음 처음 두 수를 버리는 함수이다.
// 배열의 초기값과 수행할 함수가 주어졌을 때 최종 결과를 구하는 프로그램을 작성하라.

int main() {
    // 1. 첫째 줄 테스트 케이스의 개수 T가 주어진다. T는 최대 100
    int T, n;
    string p, arr, result, temp, c;
    deque<int> dq;
    bool reverse = false, error = false;
    cin >> T;
    while (T--) {
        // 2. 케이스의 첫 줄에는 함수 p가 주어진다.  (1 ≤ p ≤ 100,000)
        cin >> p;

        // 3. 다음 줄에는 배열에 들어있는 수의 개수 n이 주어진다.  (0 ≤ n ≤ 100,000)
        cin >> n;

        // 4. 다음 줄에는 [x1,...,xn] 형태로 배열에 들어있는 정수가 주어진다. (1 ≤ xi ≤ 100)
        cin >> arr;

        // 변수 초기화
        result.clear();
        error = false;
        reverse = false;
        // 5. 입력받은 문자열을 정수 배열에 삽입
        for (int j = 0; j <= arr.length() - 1; j++) {
            c = arr[j];
            if (c == ",") {
                dq.push_back(stoi(temp));
                temp.clear();
                continue;
            } else if (c == "[")
                continue;
            else if (c == "]") {
                if (temp.size() != 0) {
                    dq.push_back(stoi(temp));
                    temp.clear();
                }
            } else
                temp.append(c);
        }

        for (int k = 0; k < p.length(); k++) {
            if (p[k] == 'R') {  // R 리버스 체크.
                if (reverse) {
                    reverse = false;
                } else
                    reverse = true;
            } else {  // D 체크
                if (dq.empty()) {
                    error = true;
                    cout << "error\n";
                    break;
                }
                if (reverse) {  // reverse가 활성화 되어있으면 뒤에서 빼기
                    dq.pop_back();
                } else {  // 그렇지 않으면 앞에서 빼기
                    dq.pop_front();
                }
            }
        }

        // 문자열 합치기
        if (error == false) {  // 에러가 활성화 되어있지 않으면
            if (dq.empty())
                result.append("[]\n");
            else {
                result.append("[");
                while (!dq.empty()) {
                    if (reverse) {
                        result.append(to_string(dq.back()));
                        result.append(",");
                        dq.pop_back();
                    } else {
                        result.append(to_string(dq.front()));
                        result.append(",");
                        dq.pop_front();
                    }
                }
                result.erase(result.length() - 1, 1);
                result.append("]\n");
            }
            cout << result;
        }
    }
}