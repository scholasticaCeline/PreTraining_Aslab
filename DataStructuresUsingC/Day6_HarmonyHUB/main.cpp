#include <stdio.h>
#include <string.h>
#include <stdlib.h>

const int size = 10;

void viewAll();

struct Table{
    char title[100];
    char artist[100];
    char genre[100];
    float rating;
    Table *next;
    Table *prev;
} *table[size], *tHead[size], *tTail[size];

Table *createTable(const char *title, const char *artist, const char *genre, float rating){
    Table *newData = (Table*)malloc(sizeof(Table));
    strcpy(newData->title, title);
    strcpy(newData->artist, artist);
    strcpy(newData->genre, genre);
    newData->rating = rating;

    newData->next = NULL;
    newData->prev = NULL;

    return newData;
}

int hash(const char *title){
    int sum = 0; 
    // const char traineeNumber[5] = "T090";
    int len = strlen(title);
    for(int i = 0; i < len; i++){
        sum += title[i];
    }
    return sum * 0 % size;
    // return sum * (int)traineeNumber[3] % size;
}

void insert(const char *title, const char *artist, const char *genre, float rating) {
    struct Table *temp = createTable(title, artist, genre, rating);
    int index = hash(title);

    if (!tHead[index]) {
        tHead[index] = tTail[index] = temp;
    } else {
        if (tHead[index]->rating < rating) {
            temp->next = tHead[index];
            tHead[index]->prev = temp;
            tHead[index] = temp;
        } else if (tTail[index]->rating >= rating) {
            tTail[index]->next = temp;
            temp->prev = tTail[index];
            tTail[index] = temp;
        } else {
            struct Table *curr = tHead[index];
            while (curr->next && curr->next->rating >= rating) {
                curr = curr->next;
            }

            temp->next = curr->next;
            temp->prev = curr;
            curr->next = temp;
            temp->next->prev = temp;
        }
    }
}


void add(){
    system("cls");
    char title[100];
    char artist[21];
    char genre[5];
    float rating;

    do{
        printf("Insert music title [more than 5 characters]: ");
        scanf("%[^\n]", title); getchar();
    } while(strlen(title) < 6);

    do{
        printf("Insert artist name [minimum 6 character maximum 20 character]: ");
        scanf("%[^\n]", artist); getchar();
    } while(strlen(artist) < 6 || strlen(artist) > 20);

    do{
        printf("Insert genre of the music [Pop | Soul | Rock]: ");
        scanf("%s", genre); getchar();
    } while(strcmp(genre, "Pop") != 0 && strcmp(genre, "Soul") != 0 && strcmp(genre, "Rock") != 0);

    do{
        printf("Insert rating of the music [minimum 1 maximum 5.0]: ");
        scanf("%f", &rating);
    } while(rating < 1.0 || rating > 5.0);

    insert(title, artist, genre, rating);

}

void viewAll(){
    system("cls");
    int isAvailable = 0;
    for(int i = 0; i < 10; i++){
        if(tHead[i]){
            isAvailable = 1; break;
        }
    }

    if(!isAvailable){
        puts("\nThere is no music at this moment");
        puts("Press enter to continue"); getchar();
        return;
    }
    printf("===============================================================================\n");
    printf("| %-25s | %-24s | %-10s | %-7s |\n", "Title", "Artist Name", "Genre", "Rating");
    printf("===============================================================================\n");
    for(int i = 0; i < size; i++){
        Table *curr = tHead[i];
        while(curr){
            printf("| %-25s | %-24s | %-10s | %-7.2f |\n", curr->title, curr->artist, curr->genre, curr->rating);
            curr = curr -> next;
        }
    }
    printf("===============================================================================\n");

    printf("\nPress enter to continue..."); getchar();    
}

void search(){
    int isAvailable = 0;
    for(int i = 0; i < 10; i++){
        if(tHead[i]){
            isAvailable = 1; break;
        }
    }

    if(!isAvailable){
        system("cls");
        puts("\nThere is no music at this moment");
        puts("Press enter to continue"); getchar();
        return;
    }

    char title[100];
    viewAll();
    puts("\nSearch Music");
    puts("================");
    printf("Insert music name you want to search [0 to go back]: ");
    scanf("%[^\n]", title); getchar();
    if(strcmp(title, "0") == 0) return;
    
    int count = 0;

    for (int i = 0; i < size; i++) {
        Table* curr = tHead[i];
        while (curr) {
            if (strcmp(curr->title, title) == 0) {
                count++; 
            }
            curr = curr->next;
        }
    }

    if (count == 0) {
        printf("\nMusic with title \"%s\" not found!\n", title);
        printf("\nPress enter to continue..."); getchar();
        return;
    }

    int index = 1;
    printf("Matching records:\n"); 
    printf("====================================================================================\n");
    printf("| No | %-25s | %-24s | %-10s | %-7s |\n", "Title", "Artist Name", "Genre", "Rating");
    printf("====================================================================================\n");
    for (int i = 0; i < size; i++) {
        Table* curr = tHead[i];
        while (curr) {
            if (strcmp(curr->title, title) == 0) {
                printf("| %d  | %-25s | %-24s | %-10s | %-7.2f |\n", index++, curr->title, curr->artist, curr->genre, curr->rating);
            }
            curr = curr->next;
        }
    }
    printf("====================================================================================\n");
}

void deletes(const char *title){
    int index = hash(title);

    if(!tHead[index]) return;

    else if(strcmp(tHead[index] -> title, title) == 0){ 
        if(strcmp(tHead[index]->title, tTail[index]->title) == 0){
            free(tHead[index]);
            tHead[index] = tTail[index] = NULL;
        } else{
            Table *temp = tHead[index] -> next;
            temp -> prev = NULL;
            tHead[index] -> next = NULL;
            free(tHead[index]);
            tHead[index] = temp;
        }
    } else if(strcmp(tTail[index] -> title, title) == 0){ 
            Table *temp = tTail[index] -> prev;
            temp -> next = NULL;
            tTail[index] -> prev = NULL;
            free(tTail[index]);
            tTail[index] = temp;
    } else{
        Table* curr = tHead[index] -> next;
        while(strcmp(curr -> title, title) != 0) curr = curr -> next;

        curr -> prev -> next = curr -> next;
        curr -> next -> prev = curr -> prev;
        curr -> prev = NULL;
        curr -> next = NULL;
        free(curr);
    }
}

void deleteTable(){
    int isAvailable = 0;
    for(int i = 0; i < 10; i++){
        if(tHead[i]){
            isAvailable = 1; break;
        }
    }

    if(!isAvailable){
        system("cls");
        puts("\nThere is no music at this moment");
        puts("Press enter to continue"); getchar();
        return;
    }

    char title[100];
    viewAll();
    puts("\nDelete Music");
    puts("==================");
    printf("\nInsert music title you want to delete [0 to go back]: ");
    scanf("%[^\n]", title); getchar();
    if(strcmp(title, "0") == 0) return;

    int found = 0; 
    int count = 0;

    for (int i = 0; i < size; i++) {
        Table* curr = tHead[i];
        while (curr) {
            if (strcmp(curr->title, title) == 0) {
                count++; 
            }
            curr = curr->next;
        }
    }

    if (count == 0) {
        printf("Music with title \"%s\" not found!\n", title);
        printf("\nPress enter to continue..."); getchar();
        return;
    }
    int index = 1;

    printf("Matching records:\n"); 
    printf("====================================================================================\n");
    printf("| No | %-25s | %-24s | %-10s | %-7s |\n", "Title", "Artist Name", "Genre", "Rating");
    printf("====================================================================================\n");
    for (int i = 0; i < size; i++) {
        Table* curr = tHead[i];
        while (curr) {
            if (strcmp(curr->title, title) == 0) {
                printf("| %d  | %-25s | %-24s | %-10s | %-7.2f |\n", index++, curr->title, curr->artist, curr->genre, curr->rating);
            }
            curr = curr->next;
        }
    }
    printf("====================================================================================\n");

    printf("Enter the number of the record you want to delete: ");
    int choice;
    scanf("%d", &choice); getchar();

    if (choice < 1 || choice > index) {
        printf("Invalid choice!\n");
        printf("\nPress enter to continue..."); getchar();
        return;
    }

    for (int i = 0; i < size; i++) {
        Table* curr = tHead[i];
        while (curr) {
            if (strcmp(curr->title, title) == 0) {
                count++;
                if (count == choice) {
                    found = 1; 
                    printf("Record \"%s\" deleted successfully.\n", curr->title);
                    deletes(title);
                    break;
                }
            }
            curr = curr->next;
        }
        if (found) break; 
    }

    if (!found) {
        printf("Invalid choice!\n");
    }

    if (!found) {
        printf("Music with title \"%s\" not found!\n", title);
    }

    printf("\nPress enter to continue..."); getchar();
}

void menu(){
    system("cls");
    puts("  _    _                                        _    _ _    _ ____");
    puts(" | |  | |                                      | |  | | |  | |  _ \\");
    puts(" | |__| | __ _ _ __ _ __ ___   ___  _ __  _   _| |__| | |  | | |_) |");
    puts(" |  __  |/ _` | '__| '_ ` _ \\ / _ \\| '_ \\| | | |  __  | |  | |  _ <");
    puts(" | |  | | (_| | |  | | | | | | (_) | | | | |_| | |  | | |__| | |_) |");
    puts(" |_|  |_|\\__,_|_|  |_| |_| |_|\\___/|_| |_|\\__, |_|  |_|\\____/|____/");
    puts("                                           __/ |");
    puts("                                          |___/");

    puts("\n1. Add Music");
    puts("2. View Music Collection");
    puts("3. Search Music");
    puts("4. Remove Music");
    puts("5. Exit");
    printf(">> ");
}

int main(){
    int choice = 0;
    do{
        menu();
        // printf("%d", hash("test"));
        scanf("%d", &choice); getchar();

        if(choice == 1){
            add();
        } else if(choice == 2){
            viewAll();
        } else if(choice == 3){
            search();
        } else if(choice == 4){
            deleteTable();
        }
    } while(choice != 5);
    

    return 0;
}