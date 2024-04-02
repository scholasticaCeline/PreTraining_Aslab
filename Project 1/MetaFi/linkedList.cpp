#include <stdio.h>
#include <string.h>
#include <stdlib.h>

struct Node{
    int openPrice;
    int closedPrice;
    int highest;
    int lowest;
    Node *next;
} *head, *tail;

Node *createNode(){
    Node *newNode = (Node*) malloc(sizeof(Node));
    return newNode;
}

Node *pushTail(){
    Node *temp = createNode();
    if(!head) head = tail = temp;
    else if(head == tail) head->next = tail;
    else{
        
    }
}