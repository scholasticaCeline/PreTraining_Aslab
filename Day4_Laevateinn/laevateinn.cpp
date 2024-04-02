#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include "logo.cpp"

struct Character {
    char name[100];
    int speed;
    char ability[100];
    char weakness[100];
    int level;
    int exp;
    int hp;
} character[7];

struct Node{ //struct buat queue
    Character data;
    Node *next;
} *head = NULL, *tail = NULL;

void battle_menu();
void viewParty_menu();
void pushMid(Character data);
void popHead();
void menu();
// void battle();
int calcHP(int level);
int calcLvl(int exp);
void initializeChara();
void initializeEnemy();
void initializeFile(FILE *fp);
char *bandingElement(const char *ability);

Node* createNode(Character data) {
    Node *newNode = (Node*)malloc(sizeof(Node));
    newNode->data = data;
    newNode->next = NULL;

    return newNode;
}

void pushMid(Character data) {
    struct Node *temp = createNode(data);
    
    if (!head) head = tail = temp;
    else if (data.speed > head->data.speed) {
        temp->next = head;
        head = temp;
    } else {
        struct Node *curr = head;
        while (curr->next && data.speed > curr->next->data.speed) {
            curr = curr->next;
        }
        temp->next = curr->next;
        curr->next = temp;
    }
}

void popHead(){
    if(!head) return;
    else if(head == tail){
        free(head);
        head = tail = NULL;
    } else{
        Node *temp = head;
        head = head->next;
        free(temp);
    }
}

void menu() {
    system("cls");
    // printf(RESET, "");
    puts(" _                           _       _");
    puts("| |                         | |     (_)"); 
    puts("| |     __ _  _____   ____ _| |_ ___ _ _ __  _ __"); 
    puts("| |    / _` |/ _ \\ \\ / / _` | __/ _ \\ | '_ \\| '_ \\"); 
    puts("| |___| (_| |  __/\\ V / (_| | ||  __/ | | | | | | |"); 
    puts("\\_____/\\__,_|\\___| \\_/ \\__,_|\\__\\___|_|_| |_|_| |_|");

    puts("\n\n");
    puts("Welcome, please choose one of the following actions:\n");
    puts("1. Battle");
    puts("2. View Party");
    puts("3. Quit");
    puts("4. Save and Quit");
    printf(">> ");
}

int calcHP(int level){
    return (150 + (level*5));
}

int calcLvl(int exp){
    return (exp/100);
}

int calcMaxExp(int level){
    return level*100;
}

void initializeChara() {
    strcpy(character[0].name, "Jogoat");
    character[0].speed = 20;
    strcpy(character[0].ability, "Inferno");
    strcpy(character[0].weakness, "Ice");
    character[0].level = 1;
    character[0].exp = 0;
    character[0].hp = calcHP(1);

    strcpy(character[1].name, "Arthur");
    character[1].speed = 28;
    strcpy(character[1].ability, "Thunderbolt");
    strcpy(character[1].weakness, "Wind");
    character[1].level = 1;
    character[1].exp = 0;
    character[1].hp = calcHP(1);

    strcpy(character[2].name, "Jack Frost");
    character[2].speed = 20;
    strcpy(character[2].ability, "Ice shards");
    strcpy(character[2].weakness, "Fire");
    character[2].level = 1;
    character[2].exp = 0;
    character[2].hp = calcHP(1);

    strcpy(character[3].name, "Mona");
    character[3].speed = 25;
    strcpy(character[3].ability, "Cyclone");
    strcpy(character[3].weakness, "Lightning");
    character[3].level = 1;
    character[3].exp = 0;
    character[3].hp = calcHP(1);
}

void initializeEnemy() {
    strcpy(character[4].name, "Fierce Cyclops");
    character[4].speed = 25;
    strcpy(character[4].ability, "Ice Shards");
    strcpy(character[4].weakness, "Fire");
    character[4].level = 1;
    character[4].exp = 0;
    character[4].hp = calcHP(1);

    strcpy(character[5].name, "Wizard");
    character[5].speed = 28;
    strcpy(character[5].ability, "Inferno");
    strcpy(character[5].weakness, "Ice");
    character[5].level = 1;
    character[5].exp = 0;
    character[5].hp = calcHP(1);

    strcpy(character[6].name, "Vicious Raven");
    character[6].speed = 40;
    strcpy(character[6].ability, "Cyclone");
    strcpy(character[6].weakness, "Lightning");
    character[6].level = 1;
    character[6].exp = 0;
    character[6].hp = calcHP(1);
}

void initializeFile(FILE *fp) {
    char name[100];
    int hp, level, exp;
    int index = 0;

    while(fscanf(fp, "%[^#]#%d#%d#%d\n", name, &hp, &level, &exp) == 4) {
        strcpy(character[index].name, name);
        character[index].hp = hp;
        character[index].level = level;
        character[index].exp = exp;
        index++;
    }
}

char *bandingElement(char *ability){
    if(strcmp(ability,"Inferno") == 0) return "Fire";
    else if(strcmp(ability, "Ice shards") == 0) return "Ice";
    else if(strcmp(ability, "Thunderbolt") == 0) return "Lightning";
    else if(strcmp(ability, "Cyclone") == 0) return "Wind";
}

void viewParty_menu(){
    system("cls");
    puts("Party Status:\n\n");
    
    for(int i = 0; i < 4; i++){
        printf("%s\n", character[i].name);
        printf("HP: %d/%d\n", character[i].hp, calcHP(character[i].level));
        printf("Speed: %d\n", character[i].speed);
        printf("Level: %d\n", character[i].level);
        printf("EXP: %d/%d\n", character[i].exp, calcMaxExp(character[i].level));
        printf("Ability: %s (%s)\n", character[i].ability, bandingElement(character[i].ability));
        printf("Weakness: %s\n\n", character[i].weakness);
    }

    printf("Press [ENTER] to continue"); getchar();
}

void battle_menu(){
    system("cls");
    srand(time(0));
    int randomEnemy = rand() % 3 + 4;

    pushMid(character[randomEnemy]);
    for (int i = 0; i < 4; i++) {
        pushMid(character[i]);
    }
    
    int pilihMenu;
    int randomEscape;
    printf("You've encountered a %s\n", character[randomEnemy].name);
    printf("%s gets the first turn\n\n", head->data.name);
    printf("Press [ENTER] to continue"); getchar();

    puts("");
    while(character[randomEnemy].hp > 0){
        printf("%s's turn, choose action:\n", character[randomEnemy].name);
        printf("1. Attack\n");
        printf("2. Use ability %s\n", character[randomEnemy].ability);
        printf("3. Rest\n");
        printf("4. Check queue\n");
        printf("5. Escape\n");
        printf(">> ");
        scanf("%d", &pilihMenu);

        switch(pilihMenu){
            case 1:
                for(int i = 0; i < 4; i++) {
                    if(character[i].hp > 0){
                        int damage = 25 + (character[i].level * 3) * (rand() % 2 + 1);
                        character[randomEnemy].hp -= damage;
                        printf("%s attacked %s for %d damage!\n", character[i].name, character[randomEnemy].name, damage);
                    }
                }
                break;
            case 2:
                for(int i = 0; i < 4; i++) {
                    if(character[i].hp > 0){
                        int damage = 25 + (character[i].level * 5);
                        character[randomEnemy].hp -= damage;
                        printf("%s used ability %s on %s for %d damage!\n", character[i].name, character[i].ability, character[randomEnemy].name, damage);
                    }
                }
                break;
            case 3:
                for(int i = 0; i < 4; i++){
                    character[i].hp += 75; 
                }
                break;
            case 4:
                printf("Next turn(s):\n");
                Node *curr = head;
                while (curr != NULL) {
                    printf("%s -> ", curr->data.name);
                    curr = curr->next;
                }
                puts("");
                printf("Press [ENTER] to continue"); getchar();
                break;

            case 5:
                randomEscape = rand() % 2;
                if (randomEscape == 1) {
                    printf("You've managed to escape the battle\n");
                    printf("Press [ENTER] to continue"); getchar();
                    return;
                } else {
                    printf("The enemy noticed you, escape attempt failed\n");
                    printf("Press [ENTER] to continue"); getchar();
                }
                break;
        }
    }

    printf("You've defeated %s\n\n", character[randomEnemy].name);
    printf("Parties gained 50 EXP\n\n");
    for(int i = 0; i < 4; i++){
        if(character[i].hp > 0){
            character[i].exp += 50;
            if (character[i].exp >= calcMaxExp(character[i].level)) {
                character[i].level++;
                character[i].hp = calcHP(character[i].level);
                printf("%s has reached Lv. %d!\n", character[i].name, character[i].level);
            }
        }
    }

    printf("Press [ENTER] to continue\n");
}

int main() {
    system("cls");
    FILE *fp = fopen("data.txt", "r"); 
    if (fp){
        initializeFile(fp);
        fclose(fp);
    } else {
        puts("Save file not found. Creating a new game...");
        initializeChara();
        sleep(2);
    }
    initializeEnemy();

    int choice = 0;
    do {
        menu();
        scanf("%d", &choice); getchar();

        switch (choice) {
            case 1: 
                battle_menu(); 
                break;
            case 2:
                viewParty_menu(); 
                break;
            case 3:
                char pilih;
                do {
                    printf("Are you sure you want to quit without saving? All of your progress will be lost [y/n]");
                    scanf(" %c", &pilih); getchar(); 
                } while(pilih != 'y' && pilih != 'n');
                if (pilih == 'y'){
                    logo();
                    exit(0); 
                } else if(pilih == 'n'){
                    FILE *fpWrite = fopen("data.txt", "w");
                    for(int i = 0; i < 4; i++){
                        fprintf(fpWrite, "%s#%d#%d#%d", character[i].name, character[i].hp, character[i].level, character[i].exp);
                    }
                    fclose(fpWrite);
                    exit(0);
                }
                break;
            case 4: 
                FILE *fpWrite = fopen("data.txt", "w");
                for(int i = 0; i < 4; i++){
                    fprintf(fpWrite, "%s#%d#%d#%d", character[i].name, character[i].hp, character[i].level, character[i].exp);
                }
                fclose(fpWrite);

                logo();
                exit(0);
        }
    } while(1); 
    
    return 0;
}
