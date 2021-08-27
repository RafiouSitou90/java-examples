package com.rafdev.activities;

import java.util.Scanner;

public class PrimeNumber {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number;

        System.out.print("Enter a number : ");
        number = sc.nextInt();

        System.out.format("Prime numbers from 0 to %d:\n", number);
        for (int i = 2; i <= number; i++) {
            if (isPrimeNumber(i)) {
                System.out.format("%d\t", i);
            }
        }
    }

    public static boolean isPrimeNumber(int number) {
        if (number < 2) {
            return false;
        }

        boolean isPrime = true;

        for (int j = 2; j < number; j++) {
            if (number % j == 0) {
                isPrime = false;
                break;
            }
        }

        return isPrime;
    }
}
