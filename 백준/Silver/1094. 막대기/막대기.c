#include<stdio.h>
int main()
{
	int x;
	int fir=64;
	int sum=0;
	int cnt=0;
	scanf("%d",&x);
	
	while(x>0)
	{
		if(fir>x)
			fir/=2;
		else 
		{
			cnt++;
			x-=fir;
		}
	}
	
	printf("%d",cnt);	
}