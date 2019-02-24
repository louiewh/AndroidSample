package com.dawang.androidsample.swordoffer;

import org.junit.Test;

public class MovingCount {


    @Test
    public void test(){
        int result = movingCount(2,10, 10);
        System.err.println(result);
    }

    public int movingCount(int threshold, int rows, int cols) {
        boolean[] flags = new boolean[rows * cols];

        return visit(threshold, rows, cols, 0, 0, flags);
    }

    int  visit(int threshold, int rows, int cols, int currentRow, int currentCol, boolean[] flags){
        int pos = currentRow * cols + currentCol;
        int count = 0;
        if(
                currentRow < 0 || currentRow >= rows ||
                        currentCol < 0 || currentCol >= cols ||
                        threshold < posSum(currentRow, currentCol) ||
                        flags[pos] == true){

            return count;
        }


        flags[pos] = true;
        count ++;

        count += visit(threshold, rows, cols, currentRow + 1, currentCol, flags);
        count += visit(threshold, rows, cols, currentRow - 1, currentCol, flags);
        count += visit(threshold, rows, cols, currentRow, currentCol + 1, flags);
        count += visit(threshold, rows, cols, currentRow, currentCol - 1, flags);

        return count;
    }

    int posSum(int row, int col){

        return intSum(row) + intSum(col);
    }

    int intSum(int intNum){
        int sum = 0;
        while(intNum != 0){
            sum += intNum % 10;
            intNum = intNum / 10 ;
        }

        return sum;
    }
}
