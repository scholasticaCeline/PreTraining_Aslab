#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main() {
    int n;
    scanf("%d", &n);
    char arr[n]; 
    scanf("%s", arr);
    
    int len = strlen(arr);
    int maxPalindrome = 1;
    int start = 0;

    for (int i = 0; i < len; i++) {
        for (int j = i; j < len; j++) { 
            int isPalindrome = 1;
            for (int k = 0; k < (j - i + 1) / 2; k++) { 
                if (arr[i + k] != arr[j - k])  isPalindrome = 0;
            }

            if (isPalindrome && (j - i + 1) > maxPalindrome) {
                start = i;
                maxPalindrome = j - i + 1;
            }
        }
    }

    printf("%d\n", maxPalindrome);

    return 0;
}