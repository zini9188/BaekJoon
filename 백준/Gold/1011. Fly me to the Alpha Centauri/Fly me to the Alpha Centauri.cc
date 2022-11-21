#include <stdio.h>
#include <math.h>
int main()
{

    int T, x, y;

    scanf("%d", &T);

    for (int i = 0; i < T; i++)
    {
        scanf("%d %d", &x, &y);

       
            int dist = y - x;

            int count = (int)sqrt(dist);

            if (sqrt(dist) == count)
            {
                printf("%d\n", count * 2 - 1);
            }
            else if (dist <= count * count + count)
            {
                printf("%d\n", count * 2);
            }
            else
            {
                printf("%d\n", count * 2 + 1);
            }
        
    }
}
