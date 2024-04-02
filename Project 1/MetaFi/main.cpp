#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>
#include "register.cpp"
#include "menu.cpp"

#define RED "\x1b[31m"
#define GREEN "\x1b[32m"
#define YELLOW "\x1b[33m"
#define BLUE "\x1b[34m"
#define MAGENTA "\x1b[35m"
#define CYAN "\x1b[36m"
#define RESET "\x1b[0m"

int main(){
    // printf(ANSI_COLOR_RED "Test\n" ANSI_COLOR_RESET);
    // printf(ANSI_COLOR_GREEN "Test\n" ANSI_COLOR_RESET);
    // printf(RESET, "Test");
    
    //Before registering
    beforeRegister();

    //User menu page
    afterRegister();

    return 0;
}