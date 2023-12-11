package org.lesson4;

public class Triangle {
     public static void main(String[] args) {
            int rows = 5;

            drawTriangle(rows);
        }

        public static void drawTriangle(int rows) {
            int row = 1;
            int spaces = rows - 1;

            while (row <= rows) {
                for (int i = 1; i <= spaces; i++) {
                    System.out.print(" ");
                }

                for (int i = 1; i <= 2 * row - 1; i++) {
                    System.out.print("*");
                }

                System.out.println();

                row++;
                spaces--;
            }
        }
    }


