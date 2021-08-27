package com.rafdev.activities;

import java.util.Scanner;

public class PerfectNumber {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int number;

        System.out.print("Enter a number : ");
        number = sc.nextInt();

        if (isPerfectNumber(number)) {
            System.out.format("%d is a perfect number!", number);
        } else {
            System.out.format("%d is not a perfect number!", number);
        }
    }

    private static boolean isPerfectNumber(int number) {
        int sum = 0;

        for (int i = 1; i < number; i++) {
            if (number % i == 0) {
                sum += i;
            }
        }

        return sum == number;
    }
}
