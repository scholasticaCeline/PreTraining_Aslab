#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <unistd.h>
#include <ctype.h>
#include <time.h>
#include "logo.cpp"

const int height = 16;
const int width = 31;
int headX, headY;
int isGameOver = 0;
int score = 1;

char town[height][width] = {
    "##############################",
    "#             ###            #",
    "#    #######  ###  #######   #",
    "#    #                #      #",
    "#    #   #########    #      #",
    "#    #   #       #    #      #",
    "#    #   #       #    #      #",
    "                              ",
    "#    #   #       #    #      #",
    "#    #   #       #    #      #",
    "#    #   #########    #      #",
    "#    #                #      #",
    "#    #######  ###  #######   #",
    "#             ###            #",
    "##############################",
};

char plaza[height][width] = {
    "##############################",
    "#      #     ###     #       #",
    "#      #     ###     #       #",
    "#      #             #       #",
    "#      #     #####   #       #",
    "#      #         #   #       #",
    "#      #         #   #       #",
    "                              ",
    "#      #         #   #       #",
    "#      #         #   #       #",
    "#      #     #####   #       #",
    "#      #             #       #",
    "#      #     ###     #       #",
    "#      #     ###     #       #",
    "##############################",
};

char garden[height][width] = {
    "##############################",
    "#            ####            #",
    "#  #     #          #     #  #",
    "#  #    #            #    #  #",
    "#  #    #            #    #  #",
    "#  #    #            #    #  #",
    "#  #    #            #    #  #",
    "                              ",
    "#  #    #            #    #  #",
    "#  #    #            #    #  #",
    "#  #    #            #    #  #",
    "#  #    #            #    #  #",
    "#  #     #          #     #  #",
    "#            ####            #",
    "##############################",
};
//town - plaza - garden

struct Snek{
    int x, y;
    Snek *next, *prev;
} *sHead, *sTail;

struct Node{
    char map[height][width];
    Node *next, *prev;
    Snek *snake;
} *head;

void mapPrint(Node *temp);
void moveSnake(char input);

Snek *createSnek(int x, int y){
    Snek *bodyAdd = (Snek*)malloc(sizeof(Snek));
    bodyAdd->x = x;
    bodyAdd->y = y;
    bodyAdd->next = NULL;
    bodyAdd->prev = NULL;

    return bodyAdd;
}

Node *createNode(char map[height][width], Snek *snake){
    Node *newNode = (Node*)malloc(sizeof(Node));

    memcpy(newNode->map, map, sizeof(newNode->map));
    newNode->next = NULL;
    newNode->prev = NULL;
    newNode->snake = snake;
    return newNode;
}

void pushQueue(int x, int y){
    Snek *temp = createSnek(x, y);
    if(!sHead) sHead = sTail = temp;
    else{
        sTail->next = temp;
        temp->prev = sTail;
        sTail = temp;
    }
}

void pushTail(char map[height][width], Snek *snake){
    Node *temp = createNode(map, snake);

    if(!head){
        head = temp;
        temp->next = head;
        head->prev = head;
    }else{
        Node *last = head->prev;

        last->next = temp;
        temp->prev = last;
        temp->next = head;
        head->prev = temp;
    }
}

void startSnek(){
    int startX = height / 2;
    int startY = width / 2;

    Snek *head = createSnek(startX, startY);
    Snek *body = createSnek(startX, startY - 1);
    head->next = body;
    body->prev = head;

    Snek *snakeNode = createSnek(startX, startY);
    pushTail(town, snakeNode);
    pushTail(town, snakeNode);
}

void randomFood(int *foodX, int *foodY, Node *apalah) {
    do {
        *foodX = rand() % height;
        *foodY = rand() % width;
    } while (apalah->map[*foodX][*foodY] != ' ');
}

void mapPrint(Node *temp, int foodX, int foodY){
    system("cls");
    srand(time(0));

    printf("Score: %d\n\n", score);

    Snek *curr = temp->snake;
    while (curr != NULL){
        if (curr == temp->snake)
            temp->map[curr->x][curr->y] = 'o';
        else
            temp->map[curr->x][curr->y] = '.';
        curr = curr->next;
    }

    if (temp->snake->y == 0){
        temp = temp->prev;
    } else if (temp->snake->y == width - 1){
        temp = temp->next;
    }

    for (int i = 0; i < height; i++){
        for (int j = 0; j < width; j++){
            if (temp->map[i][j] == ' ' && !(i == height / 2 && !(j == 0 || j == width - 1))){
                if (i == foodX && j == foodY){
                    printf("*");
                    continue;
                }
            } else if (temp->snake->x == foodX && temp->snake->y == foodY){
                score++;
                temp->map[foodX][foodY] = ' ';
                randomFood(&foodX, &foodY, temp);
            }
            printf("%c", temp->map[i][j]);
            if (temp->map[temp->snake->x][temp->snake->y] == '#'){
                isGameOver = 1;
            }
        }
        puts("");
    }
    printf("\nInput: ");
}


void moveSnake(char input){
    int snekX = 0, snekY = 0;
    switch(input) {
        case 'w':
            snekX = -1;
            snekY = 0;
            break;
        case 'a':
            snekX = 0;
            snekY = -1;
            break;
        case 's': 
            snekX = 1;
            snekY = 0;
            break;
        case 'd': 
            snekX = 0;
            snekY = 1;
            break;
        default:
            break;
    }

    Snek *curr = head->snake;
    int prevX = curr->x;
    int prevY = curr->y;
    curr->x += snekX;
    curr->y += snekY;
    curr = curr->next;

    while (curr != NULL) {
        int tempX = curr->x;
        int tempY = curr->y;
        curr->x = prevX;
        curr->y = prevY;
        prevX = tempX;
        prevY = tempY;
        curr = curr->next;
    }
}


void logic(Snek *snake){
    int foodX, foodY;
    randomFood(&foodX, &foodY, head); 

    pushTail(town, snake);
    pushTail(plaza, snake);
    pushTail(garden, snake);
    Node *currentMap = head;

    char input;
    while(!isGameOver){
        mapPrint(currentMap, foodX, foodY);
        scanf("%c", &input); getchar();
        moveSnake(input);
    }
    puts("Game Over!");
    printf("Score: %d\n", score);
    printf("Press Enter to Continue"); getchar();
}


void showMenu() {
    system("cls");
    puts(" ____       _  __ _____   ____              _");
    puts("|  _ \\ _ __(_)/ _|_   _| / ___| _ __   __ _| | _____");
    puts("| | | | '__| | |_  | |   \\___ \\| '_ \\ / _` | |/ / _ \\");
    puts("| |_| | |  | |  _| | |    ___) | | | | (_| |   <  __/");
    puts("|____/|_|  |_|_|   |_|   |____/|_| |_|\\__,_|_|\\_\\___|");
    puts("\n1. Play");
    puts("2. Exit");
    printf(">> ");
}

int main(){
    int choice = 0;
    do{
        showMenu();
        scanf("%d", &choice); getchar();

        switch (choice){
        case 1:
            startSnek();
            logic(sHead);
            break;
        }

    } while(choice != 2);

    system("cls");
    logo();
    sleep(1);

    return 0;
}