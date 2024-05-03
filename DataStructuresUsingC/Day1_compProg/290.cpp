#include <stdio.h>
#include <stdlib.h>

int main(){
    long long int n;
    int b;
    scanf("%d %lld", &b, &n);
    int convert[32];
    int count = 0;
    
    while (n > 0) {
        convert[count++] = n % b;
        n /= b;
    }
    
    for(int i = count - 1; i >= 0; i--){
        printf("%d", convert[i]);
    }
    puts("");

    return 0;
}