package mse2;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        
        double[][] matrixArr = new double[4][4];
        String[] matrixVar = {"x", "y", "z", "b"};
        String[] xyzVar = {"","x", "y", "z"};

        System.out.println("\nEnter numbers in a 3x3 matrix in this order (row = 'X', 'Y', 'Z', 'Constant'):\n");
        
        // allows the user to define a matrix
        defineMatrix(matrixArr, matrixVar);

        // if the determinant is 0 the user has to enter the array again
        matrixArr[0][0] = findDeterminant(matrixArr);
        while (matrixArr[0][0] == 0) {
            System.out.println("Determinant is 0, enter array again...\n");
            defineMatrix(matrixArr, matrixVar);
            matrixArr[0][0] = findDeterminant(matrixArr);
        }
        
        // prints the determinant from the matrix
        System.out.println("Determinant: " + matrixArr[0][0] + "\n");

        // to find dx dy dz 
        findXYZ(matrixArr);

        for (int i = 1; i < 4; i++) {
            
            double num = matrixArr[0][i] / matrixArr[0][0];
            matrixArr[0][i] = num;

            System.out.print(xyzVar[i] + " = ");
            System.out.println(matrixArr[0][i]);
        }

        System.out.println("\nArray:");
        printMatrix(matrixArr);

    }

    // MATH ALGORITHM MODULES

    public static double findDeterminant(double[][] matrixArr) {

        // rule of sarrus

        // | 1 2 3 | 1 2
        // | 4 5 6 | 4 5
        // | 7 8 9 | 7 8  

        // apply the rule of sarrus using the array coordinates

        double determinant = 

        (matrixArr[1][1] * matrixArr[2][2] * matrixArr[3][3]) +
        (matrixArr[1][2] * matrixArr[2][3] * matrixArr[3][1]) +
        (matrixArr[1][3] * matrixArr[2][1] * matrixArr[3][2]) -
        (matrixArr[3][1] * matrixArr[2][2] * matrixArr[1][3]) -
        (matrixArr[3][2] * matrixArr[2][3] * matrixArr[1][1]) -
        (matrixArr[3][3] * matrixArr[2][1] * matrixArr[1][2]);

        return determinant;
    }

    public static double[][] swapColumn(double[][] matrixArr, int col, int col2) {
        
        // Swapping elements in each row except the first row
        for (int i = 1; i < matrixArr.length; i++) {
            double temp = matrixArr[i][col2];
            matrixArr[i][col2] = matrixArr[i][col];
            matrixArr[i][col] = temp;
        }

        return matrixArr;

    }
    
    public static double[][] findXYZ(double[][] matrixArr) {
        
        for (int i = 1; i < 4; i++) {
            
            double[][] newArr = swapColumn(matrixArr, i, 0);
            double det = findDeterminant(newArr);
            matrixArr[0][i] = det;
    
            // Reverting back to the original state (except for elements in the first row)
            newArr = swapColumn(matrixArr, 0, i);

        }

        return matrixArr;
    }


    // INPUT OUTPUT METHODS

    public static void defineMatrix(double[][] matrixArr, String[] matrixVar) {

        for (int i = 1; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                System.out.print("Enter Row " + String.valueOf(i) + " Column " + matrixVar[j] + " : ");

                if (j < 3) {
                    matrixArr[i][j + 1] = inputs();
                    System.out.println("");
                }
                else
                {
                    matrixArr[i][0] = inputs();
                    System.out.println("");
                }

            }
        }
    }

    public static double inputs() {

        boolean valid = false;
        double in = 0;

        Scanner matrixScan = new Scanner(System.in);
        System.out.println("");
        
        while (!valid) {
            try {
                in = Double.parseDouble(matrixScan.nextLine());
                valid = true;
            } 
            catch (NumberFormatException e) {
                System.out.println("Invalid input.\n");
            }
        }

        return in;
    } 

    public static void printMatrix(double[][] matrixArr) {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                //System.out.printf("%.2f ", matrixArr[i][j] + " ");               
                System.out.print(matrixArr[i][j] + " ");
            }

            System.out.println();
        } 
    } 
} 
