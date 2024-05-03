#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>
#include <conio.h>
#include <unistd.h>
#include <time.h>

void menuLogic();
void howToPlay();
void menuView();
void gameLogic();
void initializeMap();
void controls();
void recurse(int i, int j);
void gameOver();

#define SHOOTER 'A'
#define BALL 'o'
#define WALL '#'
#define EMPTY ' '
#define TRAIL '.'
const int width = 23;
const int height = 20;
char map[height][width];
int score = 0;
int shooterPosX = 10;
int remaining = 126;
int numHeight = 6;
int isGameOver = 0;
int ballX = shooterPosX, ballY;
int posX, posY;
int shootCount = 10;

void menuView(){
    system("cls");
    puts("Bubble FizzPop");
    puts("");
    puts("1. Play");
    puts("2. How to Play");
    puts("3. Exit");
}

void howToPlay(){
    int page = 1;
    int detectEnter = 0;
    while (detectEnter == 0) {
        if (page == 1) {
            system("cls");
            puts("\t\tHow to Play\n");
            printf("\n\t\tPage 1-3\t\tD ->\t\t\tEnter : Exit Tutorial\n\n");
            puts("Welcome to Bubble FizzPop! Your mission is to clear the game board\n"
                 "by strategically shooting balls to destroy designated targets.\n"
                 "\n"
                 "All you have to do is destroy the specified number of balls to win the game.\n"
                 "Be strategic and efficient in your shots to survive!\n");
        } else if (page == 2) {
            system("cls");
            puts("\t\tHow to Play\n");
            printf("\n<-- A\t\tPage 2-3\t\tD -->\t\tEnter : Exit Tutorial\n\n");
            puts("Controls\n"
                 "-------\n"
                 "1. Use A and D keys to move your aiming cursor.\n"
                 "2. Press the spacebar to shoot a ball towards the targeted position.\n");
        } else if (page == 3) {
            system("cls");
            puts("\t\tHow to Play\n");
            printf("\n<-- A\t\tPage 3-3\t\tEnter : Exit Tutorial\n\n");
            puts("Gameplay\n"
                 "-------\n"
                 "1. Launch the game and observe the game board filled with various colored balls.\n"
                 "2. Navigate your aiming cursor using the arrow keys to position it over the desired target.\n"
                 "3. Press the spacebar to shoot a ball towards the selected location.\n"
                 "4. The numbers on the ball represents the amount of shots needed for it to be destroyed\n"
                 "5. A new wave of balls will emerge after some time\n");
        }
        
        if (_kbhit()) {
            char key = getch();
            if (key == 'a' || key == 'A' && page > 1) {
                page--; 
            } else if (key == 'd' || key == 'D' && page < 3) {
                page++;
            } else if (key == 13) {
                detectEnter = 1;
                menuLogic();
            }
        }
    }
}

void randomNum(int i, int j){
    if (map[i][j] != '#') {
        int randomNumber = rand() % 3 + 1;
        map[i][j] = randomNumber + '0';
    }
}

void initializeMap(){
    srand(time(0));
    //walls
    for(int i = 0; i < height; i++){
        for(int j = 0; j < width; j++){
            if(j == 0 || i == 0 || j == width - 1 ||  i == height - 1){
                map[i][j] = WALL;
            } else
                map[i][j] = EMPTY;
        }
    }

    shooterPosX = 9;
    ballX = shooterPosX;
    //random number
    for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
            if(shootCount == 10){
                randomNum(numHeight+1, j);
                shootCount = 0;
            }
            else if (j == shooterPosX) {
                map[height - 2][j] = SHOOTER;
            }
        }
    }

    for(int i = 0; i < height; i++){
        for(int j = 0; j < width; j++){
            printf("%c", map[i][j]);
        }
        puts("");
    }

    printf("\nScore: %d\n", score);
    printf("Remainder: %d\n", remaining);
}

void controls() {
    if (_kbhit()) {
        int key = getch();
        if (key == 'a'  || key == 'A' && shooterPosX > 0) { 
            shooterPosX--;
            map[height - 2][shooterPosX] = SHOOTER;
            ballX = shooterPosX;
            initializeMap();
        } else if (key == 'd' || key == 'D' && shooterPosX < width - 1) {
            shooterPosX++;
            map[height - 2][shooterPosX] = SHOOTER;
            ballX = shooterPosX;
            initializeMap();
        } else if (key == 32) {
            ballX = shooterPosX;
            ballY = height-2;
            while(map[ballX][ballY] == ' '){
                ballY--;
            }
            if(map[ballX][ballY] != ' ')
                recurse(ballX, ballY);
        }
    }
}


void recurse(int ballX, int ballY) {
    if (ballY < 0 || ballY >= height || ballX < 0 || ballX >= width || map[ballY][ballX] == '#') {
        return;
    }

    if (isdigit(map[ballY][ballX])) {
        if (map[ballY][ballX] == '1') {
            map[ballY][ballX] = ' ';
            score += 10;
            remaining--;
        } else if (map[ballY][ballX] == '2') {
            map[ballY][ballX] = '1';
            score += 1;
        } else if (map[ballY][ballX] == '3') {
            map[ballY][ballX] = '2';
            score += 2;
        }

        recurse(ballX, ballY - 1);
        recurse(ballX - 1, ballY);
        recurse(ballX, ballY + 1);
        recurse(ballX + 1, ballY);
    }
}
 

void gameLogic(){
    system("cls");
    initializeMap();
    controls();

    gameLogic();
    gameOver();
}

void gameOver(){
    if(numHeight == height-4) isGameOver = 1;
    if(isGameOver){
        initializeMap();
        printf("Press Enter to go back to the main menu...");
        getchar();
    }
}

void menuLogic() {
    char choice[10];
    while (strcmp(choice, "3") != 0) {
        menuView();
        printf(">> ");
        scanf("%s", choice); getchar();

        if (!isdigit(choice[0])) {
            printf("[!] Input must be a number!\n");
            continue;
        }

        int numChoice = atoi(choice);
        if (numChoice < 1 || numChoice > 3) {
            printf("[!] Input must be between 1 and 3!\n");
        } else {
            if (numChoice == 1) {
                gameLogic();
            } else if (numChoice == 2) {
                howToPlay();
            } else {
                puts("Thank you for playing!");
            }
            return;
        }
    }
}

int main(){
    system("cls");
    puts("Welcome to Bubble FizzPop!");
    puts("");
    puts("Press Enter to Continue...");
    getchar();

    menuLogic();

    return 0;
}
