#include <iostream>
#include <queue>

using namespace std;

int main() {
    // 프린터 기기는 순서대로 먼저 요청된 것을 먼저 인쇄한다.
    // 여러개의 문서가 쌓이면 큐 자료구조에 쌓여 FIFO에 따라 인쇄
    // 상근이가 새로 개발한 프린터
    // 1. queue의 가장 앞에 있는 문서의 중요도를 확인
    // 2. 나머지 문서들 중 현재 문서보다 중요도가 높은 문서가 하나라도 있다면, 이 문서를
    // 인쇄하지 않고 queue에 가장 뒤에 재배치. 그렇지 않다면 바로 인쇄
    // queue에 4개의 문서 abcd가 있고 중요도가 2 1 4 3이라면 c, d, a, b의 순서로 인쇄
    // 문서의 수와 중요도가 주어졌을 때 어떤 한 문서가 몇번째로 인쇄되는지 알아내는 것이다.

    // 테스트 케이스의 수
    int t, n, m, seq = 0, imp, max = 0, flag = 0;
    queue<int> doc;
    cin >> t;

    // 문서의 개수, 궁금한 문서의 현재 위치
    for (int i = 0; i < t; i++) {
        cin >> n >> m;
        while (!doc.empty()) {
            doc.pop();
        }

        for (int i = 0; i < n; i++) {
            // 중요도를 입력받아 큐에 삽입
            cin >> imp;
            doc.push(imp);
        }
        if (n == 1) {  // 1이면 1 리턴
            cout << "1\n";
            continue;
        }
        flag = 0;
        seq = 0;
        while (flag == 0) {  // 큐가 비어있을 때까지 반복
            max = 0;
            for (int i = 0; i < n; i++) {  // 최대값 찾기
                if (max < doc.front())
                    max = doc.front();
                doc.push(doc.front());
                doc.pop();
            }
            for (int i = 0; i < n; i++) {
                if (doc.front() == max) {  // 최대값과 값이 같으면 삭제
                    if (m == 0) {          // 궁금한 문서인 경우
                        doc.pop();
                        seq++;
                        flag = 1;
                        break;
                    }
                    doc.pop();
                    seq++;
                    m--;
                    break;
                } else {
                    doc.push(doc.front());
                    doc.pop();
                    if (m == 0) {
                        m = doc.size() - 1;
                    } else {
                        m--;
                    }
                }
            }
            n--;
        }
        cout << seq << endl;
    }
}
