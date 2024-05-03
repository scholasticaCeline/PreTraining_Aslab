#include <stdio.h>
#include <stdlib.h>

int main() {
    int n;
    scanf("%d", &n);

    if(n == 0)  printf("1\n");
    // else if(n == 1)  printf("1\n");
    else{
        int size = 0;
        long long int testLength = n, reverse = 0;
        while (testLength > 0){
            size++;
            testLength >>= 1;
        }

        int binary[size];
        for (int i = 0; i < size; i++){
            binary[i] = n & 1;
            n >>= 1;
        }

        for(int i = 0; i < size; i++){
            binary[i] = !binary[i];
        }

        for (int i = size - 1; i >= 0; i--){
            reverse = (reverse << 1) | binary[i];
        }
        printf("%d\n", reverse);
    }

    return 0;    
}
