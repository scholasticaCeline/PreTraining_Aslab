#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>
#include "linkedList.cpp"

void guidePrint(){
    puts("What Is Forex?");
    puts("\"Forex\", short for foreign exchange, is a global decentralized marketplace for trading currencies.");
    puts("In forex trading, participants buy one currency by selling another currency simultaneously, with the aim of profiting from fluctuations in exchange rates.");
    puts("It is one of the largest and most liquid financial markets in the world, operating 24 hours a day, five days a week.\n");

    puts("What Is Candle Stick?");
    puts("\"Candle Stick\" in charts are a type of financial chart used to represent price movements in trading markets, including forex.");
    puts("They visually display the open, high, low, and close prices for a specific period.");
    puts("If the close price is higher than the open price, the candle color will be green.");
    puts("If the close price is less than the open price, the candle color will be red.");
    puts("Shadow or tick is the highest and the lowest price of a candle.\n");

    puts("What Is Position?");
    puts("\"Position\" in forex trading refers to where we want to enter the market at certain price point.\n");

    puts("What Is Long?");
    puts("\"Long\" in forex trading refers to a trading position where a trader buys a currency pair with the expectation that its value will increase over time.\n");

    puts("What Is Short?");
    puts("\"Short\" in forex trading refers to a trading position where a trader sells a currency pair with the expectation that its value will decrease over time.\n");

    puts("What is Take Profit?");
    puts("\"Take Profit\" in forex trading refers to where we will leave the market at the specified price point.");
    puts("Our position will automatically close after the market price hits our take profit price.\n");

    puts("What Is Stop Loss?");
    puts("\"Stop Loss\" in forex trading refers to where we will leave the market at the specified price point.");
    puts("It's used to make sure that we don't lose all of our money and many more.");
    puts("Same like take profit, our position will automatically close after the market price hits our stop loss price.\n");

    printf("Press Enter To Continue"); getchar();
}

//The rest goes here

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

    } else if(choice == 3){
        guidePrint();
    }
}