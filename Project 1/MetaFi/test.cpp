#include <stdio.h>
#include <stdlib.h>
#include <time.h>

// Structure to represent a candlestick
struct Candlestick {
    int open;
    int close;
    int high;
    int low;
    struct Candlestick* prev;
    struct Candlestick* next;
};

// Function to generate a random integer between min and max
int randomInt(int min, int max) {
    return min + rand() % (max - min + 1);
}

// Function to create a new candlestick node
struct Candlestick* createCandlestick(int open, int close, int high, int low) {
    struct Candlestick* newCandlestick = (struct Candlestick*)malloc(sizeof(struct Candlestick));
    if (newCandlestick == NULL) {
        printf("Memory allocation failed.\n");
        exit(1);
    }
    newCandlestick->open = open;
    newCandlestick->close = close;
    newCandlestick->high = high;
    newCandlestick->low = low;
    newCandlestick->prev = NULL;
    newCandlestick->next = NULL;
    return newCandlestick;
}

// Function to insert a new candlestick at the end of the list
void insertCandlestickEnd(struct Candlestick** head, int open, int close, int high, int low) {
    struct Candlestick* newCandlestick = createCandlestick(open, close, high, low);
    if (*head == NULL) {
        *head = newCandlestick;
    } else {
        struct Candlestick* current = *head;
        while (current->next != NULL) {
            current = current->next;
        }
        current->next = newCandlestick;
        newCandlestick->prev = current;
    }
}

// Function to display candlesticks in the list
void displayCandlesticks(struct Candlestick* head) {
    struct Candlestick* current = head;
    while (current != NULL) {
        printf("Open: %d, Close: %d, High: %d, Low: %d\n", current->open, current->close, current->high, current->low);
        current = current->next;
    }
}

// Function to generate random candlestick data based on the given rules
void generateRandomCandlesticks(struct Candlestick** head, int numCandlesticks, int initialPrice) {
    srand(time(NULL));
    int currentPrice = initialPrice;
    for (int i = 0; i < numCandlesticks; i++) {
        int openPrice = currentPrice;
        int closePrice;
        // Close Price generation
        if (rand() % 10 == 0) { // 10% chance to be equal
            closePrice = openPrice;
        } else { // 90% chance to be different
            if (rand() % 2 == 0) { // 50% chance to be higher
                closePrice = openPrice + randomInt(10, 60);
            } else { // 50% chance to be lower
                closePrice = openPrice - randomInt(10, 60);
            }
        }
        // Highest Price generation
        int highPrice;
        if (closePrice > openPrice) {
            if (rand() % 10 == 0) { // 30% chance to be equal to close price
                highPrice = closePrice;
            } else {
                highPrice = closePrice + randomInt(0, 40);
            }
        } else {
            if (rand() % 10 == 0) { // 30% chance to be equal to open price
                highPrice = openPrice;
            } else {
                highPrice = openPrice + randomInt(0, 40);
            }
        }
        // Lowest Price generation
        int lowPrice;
        if (closePrice > openPrice) {
            if (rand() % 10 == 0) { // 30% chance to be equal to open price
                lowPrice = openPrice;
            } else {
                lowPrice = openPrice - randomInt(0, 40);
            }
        } else {
            if (rand() % 10 == 0) { // 30% chance to be equal to close price
                lowPrice = closePrice;
            } else {
                lowPrice = closePrice - randomInt(0, 40);
            }
        }
        insertCandlestickEnd(head, openPrice, closePrice, highPrice, lowPrice);
        currentPrice = closePrice;
    }
}

int main() {
    struct Candlestick* candlestickList = NULL;
    int numCandlesticks = 10; // Change this value to generate more or fewer candlesticks
    int initialPrice = 100; // Change this value to set initial price
    generateRandomCandlesticks(&candlestickList, numCandlesticks, initialPrice);
    displayCandlesticks(candlestickList);
    return 0;
}
