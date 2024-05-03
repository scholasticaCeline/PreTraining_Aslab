#include <stdio.h>
#include <string.h>

char result(char x, char y){
    if ((x == 'R' && y == 'S') || (x == 'S' && y == 'R')) return 'R';
    else if ((x == 'S' && y == 'P') || (x == 'P' && y == 'S')) return 'S';
    else if ((x == 'P' && y == 'R') || (x == 'R' && y == 'P')) return 'P';
    else return x;
    
}

char rps(char *str){
    int len = strlen(str);
    while (len > 1 && str[1] != '\0'){
        int i;
        int j = 0;
        for (i = 0; i < len - 1; i += 2){
            str[j++] = result(str[i], str[i + 1]);
        }
        if (i < len){
            str[j++] = str[i];
        }
        len = j;
    }
    return str[0];
}

int main(){
    int n;
    scanf("%d", &n);
    char str[n + 1];
    scanf("%s", str);

    printf("%c\n", rps(str));

    return 0;
}
