#include<iostream>
#include<math.h>
using std::cin;
using std::cout;

int main()
{
	float T, xOne, yOne, xTwo, yTwo, rOne, rTwo;
	float distance;
	int temp;
	cin >> T;
	for (int i = 0; i < T; i++)
	{
		cin >> xOne >> yOne >> rOne >> xTwo >> yTwo >> rTwo;
		if (rOne > rTwo)
		{
			temp = rTwo;
			rTwo = rOne;
			rOne = temp;
		}

		distance = sqrt(pow(abs(xTwo - xOne), 2) + pow(abs(yTwo - yOne), 2));
		if (xOne == xTwo && yOne == yTwo && rOne == rTwo)
		{
			cout << -1 << "\n";
		}
		else if (distance + rOne < rTwo || distance > rOne + rTwo)
		{
			cout << 0 << "\n";
		}
		else if (distance + rOne == rTwo || rOne + rTwo == distance)
		{
			cout << 1 << "\n";
		}
		else if (distance + rOne > rTwo)
		{
			cout << 2 << "\n";
		}
	}

	return 0;
}