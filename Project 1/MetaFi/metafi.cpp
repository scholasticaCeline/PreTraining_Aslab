#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

void loginAccount();
void beforeRegister();
int checkUsername(char *username);
void registerUser();

void loginAccount(){
    char email[100];
    char password[100];
    
    system("cls");
    printf("Email (0 To Exit) : ");
    scanf("%99s", email);
    if(strcmp(email, "0") == 0){
        puts("");
        beforeRegister();
    }
    printf("Password (0 To Exit) : ");
    scanf("%99s", password);
    if(strcmp(password, "0") == 0){
        puts("");
        beforeRegister();
    }

    FILE *fp = fopen("user.txt", "r");


    fclose(fp);
}

int checkUsername(char *username){
    if(strcmp(username, "0") == 0){
        puts("");
        beforeRegister();
    }

    if(strlen(username) < 1 || strlen(username) > 20){
        puts("Username length must be between 1-20 characters!");
        printf("Press Enter To Continue"); getchar();
        return 0;
    }
    for(int i = 0; i < strlen(username); i++){
        if(isalnum(username[i]) == 0 && username[i] != '_'){
            puts("Username only contains letter or numbers!");
            printf("Press Enter To Continue"); getchar();
            return 0;
        }
    }

    FILE *fpCheck = fopen("user.txt", "r");
    int alreadyExist = 0;
    while(!feof(fpCheck)){
        char tempUser[50];
        char gaguna[100];
        fscanf(fpCheck, "%[^#]#%[^\n]", tempUser, gaguna); fgetc(fpCheck);
        if(strcmp(tempUser, username) == 0) alreadyExist = 1;
    }

    if(alreadyExist){
        puts("Username already taken");
        printf("Press Enter To Continue"); getchar();
        return 0;
    }
    fclose(fpCheck);

    return 1;
}

void registerUser(){
    char username[50];
    char email[100];
    char password[100];
    system("cls");

    //Username input
    printf("Username (0 To Exit) : ");
    scanf("%49s", username);
    if(checkUsername(username) == 0) registerUser();

    //Email input
    printf("Email (0 To Exit) : ");
    scanf("%99s", email);
    if(checkEmail(email) == 0) registerUser();
    
    //Password Input
    printf("Password (0 To Exit) : ");
    scanf("%99s", password);
    if(checkPassword(password) == 0) registerUser();


}

void beforeRegister(){
    puts("Meta-Fi - Welcome");
    puts("--------------------");
    puts("1. Log in to trade");
    puts("2. Register Account");
    puts("3. Exit Application");
    printf(">> ");

    int choice;
    scanf("%d", &choice);

    if(choice == 1){
        loginAccount();
    } else if(choice == 2){
        registerUser();
    } else if(choice == 3){
        puts("Meta Fi");
        puts("");
        puts("Created By:");
        puts("Thank You For Using This Program!");
    }
}

void afterRegister(){
    puts("Meta-Fi - User's Menu");
    puts("-----------------------");
    puts("1. Start Trade");
    puts("2. Trade History");
    puts("3. Guide");
    puts("4. Log Out");
    printf(">> ");
}

int main(){
    //Before registering
    beforeRegister();

    //User menu page
    afterRegister();

    return 0;
}