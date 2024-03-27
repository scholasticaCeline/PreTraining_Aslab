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

int checkEmail(char *email){
    if(strcmp(email, "0") == 0){
        puts("");
        beforeRegister();
    }

    if(strlen(email) < 6 || strlen(email) > 36){
        puts("Username length must be between 6-36 characters!");
        printf("Press Enter To Continue"); getchar();
        return 0;
    }

    if(!isalpha(email[0])){
        puts("First character of email should be a letter.");
        printf("Press Enter To Continue"); getchar();
        return 0;
    }

    for(int i = 0; i < strlen(email); i++){
        int atCount = 0;
        if(email[i] == '#'){
            puts("Email can't contain a # symbol");
            printf("Press Enter To Continue"); getchar();
            return 0;
        } if(email[i] == ' '){
            puts("Email can't contain any space");
            printf("Press Enter To Continue"); getchar();
            return 0;
        } if(email[i] == '@'){
            atCount++;
        } 
        //validate if email has domain, ends with .com, unique (in hash table and file), contains only alphanumeric character

        if(atCount > 1){
            puts("There can only be one @ in your email");
            printf("Press Enter To Continue"); getchar();
            return 0;
        }
    }

    return 1;
}

int checkPassword(char *password){
    if(strcmp(password, "0") == 0){
        puts("");
        beforeRegister();
    }

    if(strlen(password) < 8 || strlen(password) > 36){
        puts("Username length must be between 8-36 characters!");
        printf("Press Enter To Continue"); getchar();
        return 0;
    }

    int countAlpha = 0;
    int countNum = 0;
    for(int i = 0; i < strlen(password); i++){
        if(isalpha(password[i])){
            countAlpha++;
        } else if(isdigit(password[i])){
            countNum++;
        } else{
            puts("Password cannot contain special symbols");
            printf("Press Enter To Continue"); getchar();
            return 0;
        }
    }

    if(countAlpha == 0){
        puts("Password must contain at least one alphabet or num");
        printf("Press Enter To Continue"); getchar();
        return 0;
    }

    return 1;
}

void writeToFile(char *username, char *email, char *password){
    FILE* fpWrite = fopen("user.txt", "a");
    fprintf(fpWrite,"%s#%s#%s#10000", username, email, password);
    fclose(fpWrite);
}

void usernameInput(char *username){
    printf("Username (0 To Exit) : ");
    scanf("%49s", username);
    if(checkUsername(username) == 0) usernameInput(username);
}

void emailInput(char *email){
    printf("Email (0 To Exit) : ");
    scanf("%99s", email);
    if(checkEmail(email) == 0) emailInput(email);
}

void passwordInput(char *password){
    printf("Password (0 To Exit) : ");
    scanf("%99s", password);
    if(checkPassword(password) == 0) passwordInput(password);
}

void registerUser(){
    char username[50];
    char email[100];
    char password[100];
    system("cls");

    usernameInput(username);
    emailInput(email);
    passwordInput(password);

    writeToFile(username, email, password);   
}

void beforeRegister(){
    system("cls");
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