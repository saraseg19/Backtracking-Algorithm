Author: Sarai Segura
Date: 12/1/22

Backtracking algorithm for a knapsack problem, will prompt for array size and knapsack weight.

Knapsack(int, int, int): The recursive backtracking function. Takes in integers for array index, profit, and weight. Uses the promsing function to determine if an element can be included in the knapsack.

Promising(int): The function that checks to see if the elements in the arrays can fit in the knapsack and calculates the bound for each element.

sortArrays(): Uses the values of the p[] and w[] arrays to determine which has the highest to lowest product of p/w. Uses the hashMax() function for finding the largest value and then removes value from hashmap.

hashMax(HashMap<Integer>): Is used for attaching the value of p[]/w[] at a specific index. Determines which value is the highest and returns the index of the elements.  

generateArray(int [], int[]): Takes the global variables p[] and w[] and generates values for each array. The size of these arrays will depend on user input.

bestList(boolean[]): Returns an ArrayList of the index of elements in the global variables p[] and w[] that were included in the knapsack.  

printIntArray(int []): Prints any array that are int based.

printArray(boolean []): Prints any array that are boolean based.

printArrayLIst(ArrayList<Integer>): Prints any ArrayList that are int based. 



