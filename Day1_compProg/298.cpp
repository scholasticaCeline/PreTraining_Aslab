#include <stdio.h>

int main() {
    int a, b, x, y;
    scanf("%d %d %d %d", &a, &b, &x, &y);

    int totalSteps = y - x;

    int stepsWithA = (totalSteps + a - 1) / a;
    int stepsWithB = (totalSteps + b - 1) / b;

    int minStepsNeeded = stepsWithA;
    if (b < a && stepsWithB < stepsWithA) {
        minStepsNeeded = stepsWithB;
    }

    if (x + minStepsNeeded * a == y || x + minStepsNeeded * b == y) {
        printf("%d\n", minStepsNeeded);
    } else {
        printf("-1\n");
    }

    return 0;
}