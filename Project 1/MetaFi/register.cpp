#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>
#include <unistd.h>
#include "hashTable.cpp"
#include "logo.cpp"

#define RED "\x1b[31m"
#define GREEN "\x1b[32m"
#define YELLOW "\x1b[33m"
#define BLUE "\x1b[34m"
#define MAGENTA "\x1b[35m"
#define CYAN "\x1b[36m"
#define RESET "\x1b[0m"

void loginAccount();
void beforeRegister();
int checkUsername(char *username);
int checkEmail(char *email);
int checkPassword(char *password);
void registerUser();
void writeToFile(char *username, char *email, char *password);
void usernameInput(char *username);
void emailInput(char *email);
void passwordInput(char *password);

void beforeRegister(){
    int choice;
    do{
        system("cls");
        puts(" _____ ______   _______  _________  ________  ________ ___");
        puts("|\\   _ \\  _   \\|\\  ___ \\|\\___   ___\\\\   __  \\|\\  _____\\\\  \\");
        puts("\\ \\  \\\\\\__\\ \\  \\ \\   __/\\|___ \\  \\_|\\ \\  \\|\\  \\ \\  \\__/\\ \\  \\");
        puts(" \\ \\  \\\\|__| \\  \\ \\  \\_|/__  \\ \\  \\ \\ \\   __  \\ \\   __\\\\ \\  \\");
        puts("  \\ \\  \\    \\ \\  \\ \\  \\_|\\ \\  \\ \\  \\ \\ \\  \\ \\  \\ \\  \\_| \\ \\  \\");
        puts("   \\ \\__\\    \\ \\__\\ \\_______\\  \\ \\__\\ \\ \\__\\ \\__\\ \\__\\   \\ \\__\\");
        puts("    \\|__|     \\|__|\\|_______|   \\|__|  \\|__|\\|__|\\|__|    \\|__|");
        puts("");
        puts("\t\t1. Log in to trade");
        puts("\t\t2. Register Account");
        puts("\t\t3. Exit Application");
        printf("\t\t>> ");

        scanf("%d", &choice); getchar();

        if(choice == 1) loginAccount();
        else if(choice == 2) registerUser();
    } while(choice != 3);
    
    puts("Meta Fi");
    puts("");
    puts("Created By:");
    puts("Thank You For Using This Program!");
    sleep(2);
    system("cls");
    logo();
}

int checkUsername(char *username){
    if(strcmp(username, "0") == 0){
        puts("");
        return 0;
    }

    if(strlen(username) < 1 || strlen(username) > 20){
        printf(RED, "Username length must be between 1-20 characters!\n", RESET);
        printf("Press Enter To Continue"); getchar();
        return -1;
    }
    for(int i = 0; i < strlen(username); i++){
        if(isalnum(username[i]) == 0 && username[i] != '_'){
            printf(RED, "Username only contains letter or numbers!\n");
            printf(RESET, "Press Enter To Continue"); getchar();
            return -1;
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
        printf(RED, "Username already taken\n");
        printf(RESET, "Press Enter To Continue"); getchar();
        return -1;
    }
    fclose(fpCheck);

    return 1;
}

int checkEmail(char *email){
    if(strcmp(email, "0") == 0){
        puts("");
        return 0;
    }

    if(strlen(email) < 6 || strlen(email) > 36){
        printf(RED, "Username length must be between 6-36 characters!\n", RESET);
        printf("Press Enter To Continue"); getchar();
        return -1;
    }

    if(!isalpha(email[0])){
        printf(RED, "First character of email should be a letter.\n", RESET);
        printf("Press Enter To Continue"); getchar();
        return -1;
    }

    for(int i = 0; i < strlen(email); i++){
        int atCount = 0;
        if(email[i] == '#'){
            printf(RED, "Email can't contain a # symbol\n", RESET);
            printf("Press Enter To Continue"); getchar();
            return -1;
        } if(email[i] == ' '){
            printf(RED, "Email can't contain any space\n", RESET);
            printf("Press Enter To Continue"); getchar();
            return -1;
        } if(email[i] == '@'){
            atCount++;
        } 
        //validate if email has domain, ends with .com, unique (in hash table and file), contains only alphanumeric character

        if(atCount > 1){
            printf(RED, "There can only be one @ in your email\n", RESET);
            printf("Press Enter To Continue"); getchar();
            return -1;
        }
    }

    return 1;
}

int checkPassword(char *password){
    if(strcmp(password, "0") == 0){
        puts("");
        return 0;
    }

    if(strlen(password) < 8 || strlen(password) > 36){
        printf(RED, "Username length must be between 8-36 characters!", RESET);
        printf("Press Enter To Continue"); getchar();
        return -1;
    }

    int countAlpha = 0;
    int countNum = 0;
    for(int i = 0; i < strlen(password); i++){
        if(isalpha(password[i])){
            countAlpha++;
        } else if(isdigit(password[i])){
            countNum++;
        } else{
            printf(RED, "Password cannot contain special symbols", RESET);
            printf("Press Enter To Continue"); getchar();
            return -1;
        }
    }

    if(countAlpha == 0){
        printf(RED, "Password must contain at least one alphabet or num", RESET);
        printf("Press Enter To Continue"); getchar();
        return -1;
    }

    return 1;
}

void writeToFile(char *username, char *email, char *password){
    FILE* fpWrite = fopen("user.txt", "a");
    fprintf(fpWrite,"%s#%s#%s#10000", username, email, password);
    fclose(fpWrite);
}

void usernameInput(char *username){
    system("cls");
    printf("Username (0 To Exit) : ");
    scanf("%49s", username); getchar();
    if(checkUsername(username) == -1) usernameInput(username);
}

void emailInput(char *email){
    system("cls");
    printf("Email (0 To Exit) : ");
    scanf("%99s", email); getchar();
    if(checkEmail(email) == -1) emailInput(email);
}

void passwordInput(char *password){
    system("cls");
    printf("Password (0 To Exit) : ");
    scanf("%99s", password); getchar();
    if(checkPassword(password) == -1) passwordInput(password);
}

void registerUser(){
    char username[50];
    char email[100];
    char password[100];

    usernameInput(username);
    emailInput(email);
    passwordInput(password);

    writeToFile(username, email, password);   
}

int EsameAsFile = 0;
int PsameAsFile = 0;

int emailLoginCheck(char *email){
    char gaguna1[100], emailFile[100], gaguna2[100];
    if(strcmp(email, "0") == 0){
        puts("");
        beforeRegister();
    }
    if(strlen(email) < 1){
        printf("Invalid Email!");
        printf("Press Enter To Continue"); getchar();
        return -1;
    }
}

int passLoginCheck(char *password){
    char gaguna1[100], passFile[100], gaguna2[100];
    if(strcmp(password, "0") == 0){
        puts("");
        beforeRegister();
    }
    if(strlen(password) < 1){
        printf("Invalid Email!");
        printf("Press Enter To Continue"); getchar();
        return -1;
    }
}

void loginAccount(){
    char email[100];
    char password[100];
    
    system("cls");
    printf("Email (0 To Exit) : ");
    scanf("%99s", email); getchar();
    emailLoginCheck(email);
    printf("Password (0 To Exit) : ");
    scanf("%99s", password); getchar();
    passLoginCheck(password);

    char user[100], emailFile[100], passFile[100], idk[10];
    FILE *fp = fopen("user.txt", "r");
    while(!feof(fp)){
        fscanf(fp, "%[^#]#%[^#]#%[^\n]", user, emailFile, passFile); fgetc(fp);
        if(strcmp(email, emailFile) == 0) EsameAsFile = 1;
        if(strcmp(password, passFile) == 0) PsameAsFile = 1;
    }

    if(EsameAsFile == 1 || PsameAsFile == 1){
        insertTable(user, emailFile, passFile);
    } else{
        loginAccount();
    }
}