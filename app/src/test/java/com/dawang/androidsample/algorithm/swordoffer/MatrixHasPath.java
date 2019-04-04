package com.dawang.androidsample.algorithm.swordoffer;

import org.junit.Test;

public class MatrixHasPath {

    @Test
    public void test(){
        char[] matrix = {
                'a', 'b', 'c', 'd',
                'f', 'a', 'c', 'k',
                'm', 'n', 'g', 'l',
                'o', 'p', 'x', 'y',
        };

        char[] str = {
                'a', 'b', 'c', 'd',
        };

        System.err.print(hasPath(matrix, 4, 4, str));
        char[] matrix2 = {
                'A', 'B', 'C', 'E',
                'S', 'F', 'C', 'S',
                'A', 'D', 'E', 'E',
        };

        char[] str2 = {
                'A', 'B', 'C', 'C', 'E', 'D'
        };

        char[] str3 = {
                'A', 'B', 'C', 'B'
        };

        System.err.print(hasPath(matrix2, 3, 4, str3));
    }


    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        boolean[] flags = new boolean[matrix.length];

        for(int i = 0; i < rows; i++){

            for(int j = 0; j < cols; j++){
                if(visit(matrix, rows, cols, str, i, j, 0, flags)) {
                    return true;
                }
            }
        }

        return  false;
    }

    boolean visit(char[] matrix, int rows, int cols, char[] str,
                  int currentRow, int currentCol, int index, boolean[] flags){

        int pos = currentRow*cols + currentCol;
        if(currentCol < 0 || currentRow < 0
                || currentRow >= rows || currentCol >= cols
                || matrix[pos] != str[index]
                || flags[pos] == true
                || index >= matrix.length){
            return  false;
        }

        if(index == str.length - 1) {
            return true;
        }

        flags[pos] = true;
        if (
                visit(matrix, rows, cols, str, currentRow + 1, currentCol, index+1, flags) ||
                visit(matrix, rows, cols, str, currentRow - 1, currentCol, index+1, flags) ||
                visit(matrix, rows, cols, str, currentRow, currentCol + 1, index+1, flags) ||
                visit(matrix, rows, cols, str, currentRow, currentCol - 1, index+1, flags)
                )
        {

            return true;

        }

        flags[pos] = false;

        return false;
    }
}
