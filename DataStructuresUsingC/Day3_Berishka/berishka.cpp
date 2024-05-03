#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <time.h>

struct Data {
    char productName[100];
    long long int price;
    char color[50];
    int quantity;
    char size[5];
    char ID[10];
};

Data data[100];
int count = 0;

void menu();
void addProduct_menu();
void viewProduct_menu();
void readFromFile();
void updateProducts_menu();
void removeProducts_menu();
void bubbleSort(struct Data data[], int n, char sort[]);

void bubbleSort(struct Data data[], int n, char sort[]){
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n - 1 - i; j++) {
            int cmpResult;
            if (strcmp(sort, "ID") == 0){
                cmpResult = atoi(data[j].ID + 2) - atoi(data[j + 1].ID + 2);
                // printf("ID: %d\n", cmpResult);
            } else if (strcmp(sort, "Name") == 0){
                cmpResult = strcmp(data[j].productName, data[j + 1].productName);
                // printf("Name: %d\n", cmpResult);
            } else if (strcmp(sort, "color") == 0){
                cmpResult = strcmp(data[j].color, data[j + 1].color);
                // printf("Color: %d\n", cmpResult);
            } else if (strcmp(sort, "size") == 0){
                cmpResult = strcmp(data[j].size, data[j + 1].size);
                // printf("Size: %d\n", cmpResult);
            } else if (strcmp(sort, "quantity") == 0){
                cmpResult = data[j].quantity > data[j + 1].quantity;
                // printf("Quantity: %d\n", cmpResult);
            }
            if (cmpResult > 0) {
                struct Data temp = data[j];
                data[j] = data[j + 1];
                data[j + 1] = temp;
            }
        }
    }
    viewProduct_menu();

    printf("Press [ENTER] to continue..."); getchar();
}

void readFromFile(){
    count = 0;
    FILE *fpRead = fopen("products.txt", "r");
    if (fpRead == NULL) {
        printf("No File");
        return;
    }
    while (!feof(fpRead)) {
        fscanf(fpRead, "%[^#]#%[^#]#%lld#%[^#]#%d#%[^\n]\n", data[count].ID, data[count].productName, &data[count].price, data[count].color, &data[count].quantity, data[count].size);
        // printf("%s", data[count].ID);
        // printf("%s", data[count].productName);
        // printf("%lld", data[count].price);
        // printf("%s", data[count].color);
        // printf("%d", data[count].quantity);
        // printf("%s\n", data[count].size);
        count++;
    }

    fclose(fpRead);
}

void addProduct_menu(){
    char productName[21];
    long long int price;
    char color[10];
    int quantity;
    char size[5];
    char ID[10];

    do{
        printf("Input product name [10 - 20 characters]: ");
        scanf("%[^\n]", productName); getchar();
    }while(strlen(productName) < 10 || strlen(productName) > 20);

    do{
        printf("Input product price [50000 - 100000]: ");
        scanf("%lld", &price); getchar();
    } while(price < 50000 || price > 100000);

    do{
        printf("Input product color [Black | White | Brown]: ");
        scanf("%[^\n]", color); getchar();
    } while (strcmpi(color, "Black") != 0 && strcmpi(color, "White") != 0 && strcmpi(color, "Brown") != 0);

    do{
        printf("Input product quantity [10 - 99]: ");
        scanf("%d", &quantity); getchar();
    } while(quantity < 10 || quantity > 99);

    do{
        printf("Input product size [S | M | L | XL]: ");
        scanf("%s", size); getchar();
    } while(strcmpi(size, "S") != 0 && strcmpi(size, "M") != 0 && strcmpi(size, "L") != 0 && strcmpi(size, "XL") != 0);

    srand(time(0));
    sprintf(ID, "BR%d", rand() % 999);
    // printf("%s", ID);

    FILE *fpWrite = fopen("products.txt", "a+");
    fprintf(fpWrite, "%s#%s#%lld#%s#%d#%s\n", ID, productName, price, color, quantity, size);
    fclose(fpWrite);

    printf("\nPress [ENTER] to continue"); getchar();
}

void viewProduct_menu() {    
    if (count == 0) {
        puts("=====================================================\n"
            "|              There's no product yet!              |\n"
            "=====================================================\n");

        printf("Press [ENTER] to continue"); getchar();
        return;
    }
    printf("======================================================================\n");
    printf("| ID   |  Product Name       | Color    |Size   | Quantity  | Price   |\n");
    printf("======================================================================\n");
    for(int i = 0; i < count; i++){
        printf("| %-3s| %-20s| %-9s| %-6s| %-10d| %-8lld|\n", data[i].ID, data[i].productName, data[i].color, data[i].size, data[i].quantity, data[i].price);
    }
    puts("----------------------------------------------------------------------");
    
}

void sort(){
    int sortBy;
    puts("\n");
    puts("Sort by:");
    puts("[1] ID");
    puts("[2] Name");
    puts("[3] Color");
    puts("[4] Quantity");
    puts("[5] Size");
    puts("[6] Back");
    printf(">> ");
    
    do{
        scanf("%d", &sortBy); getchar();
    } while(sortBy < 1 || sortBy > 6);

    if(sortBy == 1) bubbleSort(data, count, "ID");
    if(sortBy == 2) bubbleSort(data, count, "Name");
    if(sortBy == 3) bubbleSort(data, count, "Color");
    if(sortBy == 4) bubbleSort(data, count, "Quantity");
    if(sortBy == 5) bubbleSort(data, count, "Size");
    if(sortBy == 6) menu();
}

void updateProducts_menu() {
    char productID[10];
    readFromFile();
    viewProduct_menu();
    printf("Input product ID to update: ");
    scanf("%s", productID); getchar();

    int found = 0;
    for(int i = 0; i < count; i++) {
        if(strcmp(productID, data[i].ID) == 0) {
            found = 1;
            break;
        }
    }
    if(!found){
        puts("Product not found!\n");

        printf("Press [ENTER] to continue"); getchar();
        return;
    }

    int i;
    for(i = 0; i < count ; i++){
        if(strcmpi(productID, data[i].ID) == 0) {
            do {
                printf("Input product name [10 - 20 characters]: ");
                scanf(" %[^\n]", data[i].productName); getchar();
            } while(strlen(data[i].productName) < 10 || strlen(data[i].productName) > 20);

            do {
                printf("Input product price [50000 - 100000]: ");
                scanf("%lld", &data[i].price); getchar();
            } while(data[i].price < 50000 || data[i].price > 100000);

            do {
                printf("Input product color [Black | White | Brown]: ");
                scanf(" %[^\n]", data[i].color); getchar();
            } while(strcmpi(data[i].color, "Black") != 0 && strcmpi(data[i].color, "White") != 0 && strcmpi(data[i].color, "Brown") != 0);

            do {
                printf("Input product quantity [10 - 99]: ");
                scanf("%d", &data[i].quantity); getchar();
            } while(data[i].quantity < 10 || data[i].quantity > 99);

            do {
                printf("Input product size [S | M | L | XL]: ");
                scanf("%s", data[i].size); getchar();
            } while(strcmpi(data[i].size, "S") != 0 && strcmpi(data[i].size, "M") != 0 && strcmpi(data[i].size, "L") != 0 && strcmpi(data[i].size, "XL") != 0);

            printf("Product details updated successfully.\n");
            printf("Press [ENTER] to continue..."); getchar();
            break;
        }
    }

    FILE *fileRewrite = fopen("products.txt",  "w+");

    for(int j = 0; j < count; j++) {
        fprintf(fileRewrite, "%s#%s#%lld#%s#%d#%s\n", data[j].ID, data[j].productName, data[j].price, data[j].color, data[j].quantity, data[j].size);
    }
    fclose(fileRewrite);
}

void deletes(char *removeID) {
    int found = 0;
    for(int i = 0; i < count; i++) {
        if(strcmp(removeID, data[i].ID) == 0) {
            found = 1;
            for(int j = i; j < count - 1; j++) {
                data[j] = data[j + 1];
            }
            count--;
            break;
        }
    }

    if(!found){
        puts("Product not found!\n");

        printf("Press [ENTER] to continue"); getchar();
        return;
    }
    FILE *fileDel = fopen("products.txt",  "w+");

    for(int j = 0; j < count; j++) {
        fprintf(fileDel, "%s#%s#%lld#%s#%d#%s\n", data[j].ID, data[j].productName, data[j].price, data[j].color, data[j].quantity, data[j].size);
    }
    fclose(fileDel);
    puts("\nProduct removed successfully"); 
    printf("Press [ENTER] to continue"); getchar();
}


void removeProducts_menu(){
    char removeID[10];
    readFromFile();
    viewProduct_menu();
    printf("Input product ID to remove: ");
    scanf("%s", removeID); getchar();
    deletes(removeID);
}

void menu(){
    puts(" /$$$$$$$                    /$$$$$$  /$$$$$$  /$$       /$$\n"
        "| $$__  $$                  |_  $$_/ /$$__  $$| $$      | $$\n"
        "| $$  \\ $$  /$$$$$$   /$$$$$$ | $$  | $$  \\__/| $$$$$$$ | $$   /$$  /$$$$$$\n"
        "| $$$$$$$  /$$__  $$ /$$__  $$| $$  |  $$$$$$ | $$__  $$| $$  /$$/ |____  $$\n"
        "| $$__  $$| $$$$$$$$| $$  \\__/| $$   \\____  $$| $$  \\ $$| $$$$$$/   /$$$$$$$\n"
        "| $$  \\ $$| $$_____/| $$      | $$   /$$  \\ $$| $$  | $$| $$_  $$  /$$__  $$\n"
        "| $$$$$$$/|  $$$$$$$| $$     /$$$$$$|  $$$$$$/| $$  | $$| $$ \\  $$|  $$$$$$$\n"
        "|_______/  \\_______/|__/    |______/ \\______/ |__/  |__/|__/  \\__/ \\_______/\n");
    puts("1. Add product");
    puts("2. View Product");
    puts("3. Update Product");
    puts("4. Remove Product");
    puts("5. Exit");
    printf(">> ");
}


int main() {
    int choice = 0;
    while(choice != 5) {
        readFromFile();
        menu();
        scanf("%d", &choice); getchar(); 
        switch (choice) {
            case 1:
                addProduct_menu();
                break;
            case 2:
                readFromFile();
                viewProduct_menu();
                sort();
                break;
            case 3:
                updateProducts_menu();
                break;
            case 4:
                removeProducts_menu();
                break;
            case 5:
                break;
            default:
                puts("Invalid option");
                printf("Press [ENTER] to continue"); getchar();
                break;
        }
    }
    
    printf("Goodbye");

    return 0;
}