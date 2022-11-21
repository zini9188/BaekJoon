#include<stdio.h>


int main()
{
	int iT;
	int iN;

	scanf("%d", &iT);

	for (int i = 0; i < iT; i++)
	{
		scanf("%d", &iN);

		int ia = 1;
		int ib = 1;
		int itmp = 0;

		if (iN == 0) printf("1 0\n");
		else if (iN == 1) printf("0 1\n");
		else if (iN == 2) printf("1 1\n");
		else
		{
			for (int i = 0; i < iN - 2; i++)
			{
				itmp = ib;
				ib += ia;
				ia = itmp;
			}
			printf("%d %d\n", ia, ib);
		}
	}

}