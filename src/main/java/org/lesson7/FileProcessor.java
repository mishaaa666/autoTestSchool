package org.lesson7;

import java.io.*;

public class FileProcessor {

    public static void main(String[] args) {
        try {
            readFile("example.txt");
        } catch (CustomUncheckedException ce) {
            System.out.println("Caught CustomException: " + ce.getMessage());
        } catch (Exception e) {
            System.out.println("Caught Exception: " + e.getMessage());
        }
    }

    public static void readFile(String file) {
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Exception occurred " + e.getMessage());
            throw new CustomUncheckedException("Error while reading file", e);
        }
    }
}