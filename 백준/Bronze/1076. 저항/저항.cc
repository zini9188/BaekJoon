#include <iostream>
#include <string>
using namespace std;
 
int colorValue[3];
string color[3];
pair<int, long long> resistance[10];
 
enum{black=0, brown, red, orange, yellow, green, blue, violet, grey, white};
 
void Initialize(void)
{
        long long multiple = 1;
        for (int i = 0; i < 10; i++)
        {
                 resistance[i].first = i;
                 resistance[i].second = multiple;
                 multiple *= 10;
        }
}
 
long long getResistanceValue(void)
{
        int firstValue = resistance[colorValue[0]].first; //첫번째 저항 값
        int secondValue = resistance[colorValue[1]].first; //두번째 저항 값
 
        long long result = firstValue * 10 + secondValue; //(첫번째 저항값*10 + 두번째 저항값)
        result *= resistance[colorValue[2]].second; //위 값에 세번째 저항 곱 곱함
        return result;
}
 
int main(void)
{
        Initialize();
 
        for (int i = 0; i < 3; i++)
        {
                 cin >> color[i];
                 if (color[i] == "black")
                         colorValue[i] = black;
                 else if (color[i] == "brown")
                         colorValue[i] = brown;
                 else if (color[i] == "red")
                         colorValue[i] = red;
                 else if (color[i] == "orange")
                         colorValue[i] = orange;
                 else if (color[i] == "yellow")
                         colorValue[i] = yellow;
                 else if (color[i] == "green")
                         colorValue[i] = green;
                 else if (color[i] == "blue")
                         colorValue[i] = blue;
                 else if (color[i] == "violet")
                         colorValue[i] = violet;
                 else if (color[i] == "grey")
                         colorValue[i] = grey;
                 else
                         colorValue[i] = white;
        }
 
        cout << getResistanceValue() << endl;
        return 0;
}