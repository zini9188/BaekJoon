#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <ctime>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);
    cout.tie(nullptr);
    time_t timer = time(NULL);
    struct tm *t = localtime(&timer);
    printf("%d-%d-%d", t->tm_year + 1900, t->tm_mon + 1, t->tm_mday);
}
