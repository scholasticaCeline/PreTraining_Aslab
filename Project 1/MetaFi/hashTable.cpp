#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

const int size = 26;

//hash table initilaization
struct Account{
    char *email;
    char *username;
    char *password;
    Account *next, *prev;
} *table[size], *head[size], *tail[size];

Account *createTable(const char *username, const char *email, const char *password){
    Account *newData = (Account*)malloc(sizeof(Account));
    strcpy(newData -> username, username);
    strcpy(newData -> email, email);
    strcpy(newData -> password, password);
    newData -> next = NULL;
    newData -> prev = NULL;
    
    return newData;
}

int searchHash(const char *username) {
    int index = searchHash(username);
    Account *curr = head[index];
    while(curr) {
        if(strcmp(curr->username, username) == 0) {
            return 1;
        }
        curr = curr->next;
    }
    return NULL;
}

int hashUsername(const char *username) {
    int sum = 0;
    int len = strlen(username);
    for (int i = 0; i < len; i++) {
        sum += username[i];
    }
    return sum % 26;
}

void insertTable(const char *username, const char *email, const char *password){
    Account *temp = createTable(username, email, password);
    int index = hashUsername(username);

    if(!head[index])  head[index] = tail[index] = temp;
    
    else{
        if(strcmp(head[index] -> username, username) > 0){ //push head
            temp-> next = head[index];
            head[index]->prev = temp;
            head[index] = temp;
        } else if(strcmp(tail[index]->username,username) <= 0){ //push tail
            tail[index]->next = temp;
            temp->prev = tail[index];
            tail[index] = temp;
        } else{ // push mid
            Account *curr = head[index] -> next;
            while(strcmp(curr->username,username) <= 0) curr = curr -> next;           

            curr -> prev -> next  = temp;
            temp -> prev = curr -> prev;
            temp -> next = curr;
            curr -> prev = temp;
        }
    }
}