#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>
#include <time.h>
#include <unistd.h>
#include "logo.cpp"
#include "dataStruct.cpp"

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
void writeToFile(char *username, char *email, char *password, long int price);
void usernameInput(char *username);
void emailInput(char *email);
void passwordInput(char *password);

//REGISTER PAGE
void beforeRegister(){
    int choice;
    do{
        printf(RESET);
        system("cls");
        puts(" _____ ______   _______  _________  ________  ________ ___");
        puts("|\\   _ \\  _   \\|\\  ___ \\|\\___   ___\\\\   __  \\|\\  _____\\\\  \\");
        puts("\\ \\  \\\\\\__\\ \\  \\ \\   __/\\|__ \\  \\_|\\ \\  \\|\\  \\ \\  \\__/\\ \\  \\");
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
    exit(0);
}

int checkUsername(char *username){
    if(strcmp(username, "0") == 0){
        puts("");
        return 0;
    }
    int lenUser = strlen(username);

    if(strlen(username) < 1 || lenUser > 20){
        printf(RED"Username length must be between 1-20 characters!\n"RESET);
        printf("Press Enter To Continue"); getchar();
        return -1;
    }
    for(int i = 0; i < lenUser; i++){
        if(isalnum(username[i]) == 0 && username[i] != '_'){
            printf(RED"Username only contains letter or numbers!\n"RESET);
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
        printf(RED"Username already taken\n"RESET);
        printf("Press Enter To Continue"); getchar();
        return -1;
    }
    fclose(fpCheck);

    return 1;
}

int checkEmail(char *email) {
    if(strcmp(email, "0") == 0) {
        puts("");
        return 0;
    }

    if(strlen(email) < 6 || strlen(email) > 36) {
        printf("Email length must be between 6-36 characters!\n"RESET);
        printf("Press Enter To Continue"); getchar();
        return -1;
    }

    if(!isalpha(email[0])) {
        printf("First character of email should be a letter.\n"RESET);
        printf("Press Enter To Continue"); getchar();
        return -1;
    }
    
    int lenEmail = strlen(email);
    int atCount = 0;
    int dotCount = 0;
    for(int i = 0; i < lenEmail; i++) {
        if(email[i] == '#') {
            printf("Email can't contain a # symbol\n"RESET);
            printf("Press Enter To Continue"); getchar();
            return -1;
        } 
        if(email[i] == ' ') {
            printf("Email can't contain any space\n"RESET);
            printf("Press Enter To Continue"); getchar();
            return -1;
        } 
        if(email[i] == '@') {
            atCount++;
        } 
        if(email[i] == '.') {
            dotCount++;
        }
        if(!isalnum(email[i]) && email[i] != '@' && email[i] != '.') {
            printf("Email can only contain alphanumeric characters, @, and .\n"RESET);
            printf("Press Enter To Continue"); getchar();
            return -1;
        }
    }

    if(atCount != 1) {
        printf("There must only one @ in email\n"RESET);
        printf("Press Enter To Continue"); getchar();
        return -1;
    }

    if(dotCount == 0) {
        printf("Email must contain a dot\n"RESET);
        printf("Press Enter To Continue"); getchar();
        return -1;
    }

    if(email[lenEmail - 4] != '.' || email[lenEmail - 3] != 'c' || email[lenEmail - 2] != 'o' || email[lenEmail - 1] != 'm') {
        printf(RED"Email must end with '.com'\n"RESET);
        printf("Press Enter To Continue"); getchar();
        return -1;
    }
    FILE *fpCheck = fopen("user.txt", "r");
    int alreadyExist = 0;
    while(!feof(fpCheck)){
        char emailUser[50];
        char gaguna[100];
        fscanf(fpCheck, "%[^#]#%[^#]#%[^\n]", gaguna, emailUser, gaguna); fgetc(fpCheck);
        if(strcmp(emailUser, email) == 0) alreadyExist = 1;
    }
    if(alreadyExist){
        printf(RED"Email already taken\n"RESET);
        printf("Press Enter To Continue"); getchar();
        return -1;
    }
    fclose(fpCheck);

    if(searchHash(email)){
        printf(RED "Email already taken\n");
        printf("Press Enter To Continue"); getchar();
        return -1;
    }

    return 1;
}

int checkPassword(char *password){
    if(strcmp(password, "0") == 0){
        puts("");
        return 0;
    }

    if(strlen(password) < 8 || strlen(password) > 36){
        printf( "Username length must be between 8-36 characters!");
        printf("Press Enter To Continue"); getchar();
        return -1;
    }

    int countAlpha = 0;
    int countNum = 0;
    int lenPass = strlen(password);
    for(int i = 0; i < lenPass; i++){
        if(isalpha(password[i])){
            countAlpha++;
        } else if(isdigit(password[i])){
            countNum++;
        } else{
            printf( "Password cannot contain special symbols");
            printf("Press Enter To Continue"); getchar();
            return -1;
        }
    }

    if(countAlpha == 0){
        printf( "Password must contain at least one alphabet or num");
        printf("Press Enter To Continue"); getchar();
        return -1;
    }

    return 1;
}

void writeToFile(char *username, char *email, char *password, long int price){
    FILE* fpWrite = fopen("user.txt", "a");
    fprintf(fpWrite,"%s#%s#%s#%d", username, email, password, price);
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

    writeToFile(username, email, password, 10000);   
}

// LOGIN PAGE
int EsameAsFile = 0;
int PsameAsFile = 0;

int emailLoginCheck(char *email){
    if(strlen(email) == 0){
        printf(RED "Invalid Email!" RESET);
        printf("Press Enter to Continue"); getchar();
        loginAccount();
    }

    if(strcmp(email, "0") == 0){
        puts("");
        beforeRegister();
    }
    if(strlen(email) < 1){
        printf("Invalid Email!");
        printf("Press Enter To Continue"); getchar();
        return -1;
    }
    return 1;
}

int passLoginCheck(char *password){
    if(strcmp(password, "0") == 0){
        puts("");
        beforeRegister();
    }
    if(strlen(password) < 1){
        printf("Invalid Email!");
        printf("Press Enter To Continue"); getchar();
        return -1;
    }
    return 1;
}

void loginAccount(){
    char email[100];
    char password[100];
    
    system("cls");
    printf("Email (0 To Exit) : ");
    scanf("%s", email); getchar();
    emailLoginCheck(email);

    printf("Password (0 To Exit) : ");
    scanf("%s", password); getchar();
    passLoginCheck(password);

    char user[100], emailFile[100], passFile[100];
    FILE *fp = fopen("user.txt", "r");
    while(!feof(fp)){
        fscanf(fp, "%[^#]#%[^#]#%[^\n]", user, emailFile, passFile); fgetc(fp);
        if(strcmp(email, emailFile) == 0) EsameAsFile = 1;
        if(strcmp(password, passFile) == 0) PsameAsFile = 1;
    }

    if(EsameAsFile == 1 || PsameAsFile == 1){
        insertTable(user, emailFile, passFile);
    } else{
        printf(RED "Email or Password Is Invalid!\n"RESET);
        printf("Press Enter to Continue\n"); getchar();
        loginAccount();
    }
    fclose(fp);
}


//TRADE
void guidePrint(){
    puts(BLUE"What Is Forex?"RESET);
    puts("\"Forex\", short for foreign exchange, is a global decentralized marketplace for trading currencies.");
    puts("In forex trading, participants buy one currency by selling another currency simultaneously, with the aim of profiting from fluctuations in exchange rates.");
    puts("It is one of the largest and most liquid financial markets in the world, operating 24 hours a day, five days a week.\n");

    puts(YELLOW"What Is Candle Stick?"RESET);
    puts("\"Candle Stick\" in charts are a type of financial chart used to represent price movements in trading markets, including forex.");
    puts("They visually display the open, high, low, and close prices for a specific period.");
    puts("If the close price is higher than the open price, the candle color will be green.");
    puts("If the close price is less than the open price, the candle color will be red.");
    puts("Shadow or tick is the highest and the lowest price of a candle.\n");

    puts(MAGENTA"What Is Position?"RESET);
    puts("\"Position\" in forex trading refers to where we want to enter the market at certain price point.\n");

    puts(GREEN"What Is Long?"RESET);
    puts("\"Long\" in forex trading refers to a trading position where a trader buys a currency pair with the expectation that its value will increase over time.\n");

    puts(YELLOW"What Is Short?"RESET);
    puts("\"Short\" in forex trading refers to a trading position where a trader sells a currency pair with the expectation that its value will decrease over time.\n");

    puts(GREEN"What is Take Profit?"RESET);
    puts("\"Take Profit\" in forex trading refers to where we will leave the market at the specified price point.");
    puts("Our position will automatically close after the market price hits our take profit price.\n");

    puts(RED"What Is Stop Loss?"RESET);
    puts("\"Stop Loss\" in forex trading refers to where we will leave the market at the specified price point.");
    puts("It's used to make sure that we don't lose all of our money and many more.");
    puts("Same like take profit, our position will automatically close after the market price hits our stop loss price.\n");

    printf("Press Enter To Continue"); getchar();
}

int randomRandom(int x, int y){
    srand(time(0));
    return x + rand() % (y - x);
}

void display(){
    printf("╔═══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╦═════╗\n");

    struct Node* curr = head;
    while (curr != NULL) {
        printf("║ Open: %3d, Close: %3d, High: %3d, Low: %3d", curr->openPrice, curr->closedPrice, curr->highest, curr->lowest);

        for (int i = 0; i < 79; i++) {
            if (curr->highest == curr->closedPrice) {
                if (i == 30) {
                    printf("│");
                } else {
                    printf(" ");
                }
            } else if (curr->highest == curr->openPrice) {
                if (i == 30) {
                    printf("│");
                } else {
                    printf("-");
                }
            } else if (curr->lowest == curr->closedPrice) {
                if (i == 30) {
                    printf("│");
                } else {
                    printf(" ");
                }
            } else if (curr->lowest == curr->openPrice) {
                if (i == 30) {
                    printf("│");
                } else {
                    printf("-");
                }
            } else {
                printf(" ");
            }
        }

        if (curr->openPrice == curr->closedPrice) {
            printf("██");
        } else if (curr->openPrice == curr->closedPrice + 10) {
            printf("│█");
        } else {
            printf("  ");
        }

        printf(" ║ %3d ║\n", curr->closedPrice);

        curr = curr->next;
    }
    printf("╠══════════════════════════════════════╩══════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝\n");
    menuTrade();   
}

void generateRandom(){
    srand(time(0));
    int open, close, high, low;
    int currentPrice = 100; //anggep aja initial pricenya 100
    int random = rand() % 100;
    
    open = currentPrice;
    if(random < 10) close = open;
    else{
        int highOrNo = rand() % 2;
        if(highOrNo == 0){
            close = open + randomRandom(10, 60);
        } else{
            close = open - randomRandom(10, 60);
        }
    }

    if(close > open){ //for highest
        if(random < 30){
            high = close;
        } else{
            high = close + randomRandom(0, 40);
        }
    } else{
        random = rand() % 100;
        if(random < 30){
            high = open;
        } else{
            high = open + randomRandom(0, 40);
        }
    }

    if(close > open){ //for lowest
        if(random < 30){
            low = open;
        } else{
            low = open + randomRandom(0, 40);
        }
    } else{
        random = rand() % 100;
        if(random < 30){
            low = close;
        } else{
            low = close + randomRandom(0, 40);
        }
    }
    
    currentPrice = close;
    pushTail(open, close, high, low);
}

void trade(){
    for(int i = 0; i < 20; i++){
        generateRandom();
    }

    display();
}

void menuTrade(){
    printf("Welcome To METAFI, %s\n", currUser->username);
    puts("1. New Day");
    puts("2. Previous");
    puts("3. Next");
    puts("4. Long");
    puts("5. Short");
    puts("6. Logout");
    printf(">> ");
    int choice;
    scanf("%d", &choice); getchar();
    if(choice == 1){
        generateRandom();
    } else if(choice == 2){
        
    }
}

void viewHistory(){
    if (hHead == NULL){
        printf("No Trade History.\n");
        printf("Press Enter to Continue"); getchar();
        return;
    }
    
    int count = 1;
    if (currUser != NULL) {
    History *curr = currUser->history;
        while (curr != NULL) {
            printf("No: %d\n", count++);
            printf("Value\t: %d\nPosition\t: %d\nProfit\t: %d\nStop Loss\t: %d\nReward: %d\n", curr->value, curr->position, curr->profit, curr->stopLoss, curr->reward);
            curr = curr->next;
        }
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

    int choice;
    scanf("%d", &choice); getchar();
    if(choice == 1){
        
    } else if(choice == 2){
        viewHistory();
    } else if(choice == 3){
        guidePrint();
    }
}

int main(){
    // printf(ANSI_COLOR_RED "Test\n" ANSI_COLOR_RESET);
    // printf(ANSI_COLOR_GREEN "Test\n" ANSI_COLOR_RESET);
    // printf(RESET);
    // printf("%c", 216);
    // printf("%c", 179);

    //Before registering
    beforeRegister();

    //User menu page
    afterRegister();

    return 0;
}