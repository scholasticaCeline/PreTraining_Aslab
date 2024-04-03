#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

const int size = 26;

struct Node{
    int openPrice;
    int closedPrice;
    int highest;
    int lowest;
    Node *next;
    Node *prev;
} *head, *tail;

struct History{
    int value;
    int position;
    int profit;
    int stopLoss;
    int reward;
    History *next;
} *hHead, *hTail;

//TABLE
struct Account{
    char *email;
    char *username;
    char *password;
    History *history;
    Node *candleSticks;
    Account *next, *prev;
} *table[size], *tHead[size], *tTail[size];

//TABLE
Account *createTable(const char *username, const char *email, const char *password){
    Account *newData = (Account*)malloc(sizeof(Account));
    strcpy(newData->username, username);
    strcpy(newData->email, email);
    strcpy(newData->password, password);
    newData->next = NULL;
    newData->prev = NULL;
    
    return newData;
}

int hashUsername(const char *username) {
    int sum = 0;
    int len = strlen(username);
    for (int i = 0; i < len; i++) {
        sum += username[i];
    }
    return sum % 26;
}

int searchHash(const char *email) {
    int index = hashUsername(email);
    Account *curr = tHead[index];
    while(curr) {
        if(strcmp(curr->email, email) == 0){
            return 1;
        } 
        curr = curr->next;
    }
    return 0;
}

void insertTable(const char *username, const char *email, const char *password){
    Account *temp = createTable(username, email, password);
    int index = hashUsername(username);

    if(!tHead[index])  tHead[index] = tTail[index] = temp;
    else{
        if(strcmp(tHead[index]->username, username) > 0){ 
            temp->next = tHead[index];
            tHead[index]->prev = temp;
            tHead[index] = temp;
        } else if(strcmp(tTail[index]->username, username) <= 0){ 
            tTail[index]->next = temp;
            temp->prev = tTail[index];
            tTail[index] = temp;
        } else{ 
            Account *curr = tHead[index]->next;
            while(curr && strcmp(curr->username,username) <= 0) curr = curr->next;

            curr->prev->next = temp;
            temp->prev = curr->prev;
            temp->next = curr;
            curr->prev = temp;
        }
    }
}

//LL
Node *createNode(int openPrice, int closedPrice, int highest, int lowest){
    Node *newNode = (Node*) malloc(sizeof(Node));

    newNode->openPrice = openPrice;
    newNode->closedPrice = closedPrice;
    newNode->highest = highest;
    newNode->lowest = lowest;
    newNode->next = NULL;
    newNode->prev = NULL;

    return newNode;
}

Node *pushTail(int openPrice, int closedPrice, int highest, int lowest){
    Node *temp = createNode(openPrice, closedPrice, highest, lowest);
    if(!head) head = tail = temp;
    else if(head == tail) head->next = tail;
    else{
        tail->next = temp;
        temp->prev = tail;
        tail = temp;
    }
    return temp;
}
