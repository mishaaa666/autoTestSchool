package org.lesson4;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {
        int[] intArray = {31, 432, 3, 42, 51, 999, -1};
        int temp;


        for (boolean flag = true; flag != false; ) {
            flag = false;

            for (int i = 0; i < intArray.length - 1; i++) {
                if (intArray[i] > intArray[i + 1]) {
                    temp = intArray[i];
                    intArray[i] = intArray[i + 1];
                    intArray[i + 1] = temp;
                    flag = true;
                }
            }
            System.out.println("Sorted array = " + Arrays.toString(intArray));

        }

    }

}
