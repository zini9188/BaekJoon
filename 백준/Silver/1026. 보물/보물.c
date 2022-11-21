#include<stdio.h>
int main(){
    int n, temp, sum = 0;
    int a[50];
    int b[50];

    scanf("%d", &n);

    for(int i=0; i<n; i++) //a입력
        scanf("%d", &a[i]);

    for(int i=0; i<n; i++)//b입력
        scanf("%d", &b[i]);

    for(int i=0; i<n-1; i++){ //a내림차순
        for(int j=0; j<n-1-i; j++){
            if(a[j] < a[j+1]){
                temp = a[j];
                a[j] = a[j+1];
                a[j+1] = temp;
            }
        }
        for(int j=0; j<n-1-i; j++){ //b오름차순
            if(b[j] > b[j+1]){
                temp = b[j];
                b[j] = b[j+1];
                b[j+1] = temp;
            }
        }
    }
    for(int i=0; i<n; i++){
        sum+=a[i]*b[i];
    }
    printf("%d", sum);
}