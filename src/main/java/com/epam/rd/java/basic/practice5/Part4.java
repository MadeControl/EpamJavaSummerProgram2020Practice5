package com.epam.rd.java.basic.practice5;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Part4 {

    private static final String FILE_NAME = "part4.txt";
    private static final Logger LOGGER = Logger.getLogger(Part4.class.getName());
    private static int[][] matrixDigits;

    public static void main(final String[] args) {



    }


    private static int[][] returnMatrixDigits(String matrixDigitsInString){

        String[] stringsOfMatrixDigits = matrixDigitsInString.split(System.lineSeparator());
        int[][] matrix = new int[stringsOfMatrixDigits.length][];

        for(int i = 0; i < matrix.length; i++){

            String[] arrayOfDigitsInString = stringsOfMatrixDigits[i].split(" ");
            int[] arrayDigits = new int[arrayOfDigitsInString.length];

            for(int j = 0; j < arrayOfDigitsInString.length; j++){

                arrayDigits[j] = Integer.parseInt(arrayOfDigitsInString[j].trim());

            }
            matrix[i] = arrayDigits;

        }
        return  matrix;

    }

    private static String readFromFile() {

        StringBuilder stringBuilder = new StringBuilder();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_NAME))){

            String string;

            while ((string = bufferedReader.readLine()) != null){
                stringBuilder.append(string).append(System.lineSeparator());
            }

        } catch (IOException ex){
            LOGGER.log(Level.SEVERE, "IOException", ex);
        }

        return stringBuilder
                .deleteCharAt(stringBuilder.length()-1)
                .toString();
    }

}
