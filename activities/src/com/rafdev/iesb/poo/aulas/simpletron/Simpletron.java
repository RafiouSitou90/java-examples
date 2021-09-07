package com.rafdev.iesb.poo.aulas.simpletron;

import java.util.Scanner;

public class Simpletron {
    int ip = 0;
    int acc = 0;
    int[] memory = new int[100];

    void read(int position) {
        Scanner sc = new Scanner(System.in);
        String data = sc.nextLine();
        sc.close();
        memory[position] = Integer.parseInt(data);
    }

    void write(int position) {
        System.out.println(memory[position]);
    }

    void load(int position) {
        acc = memory[position];
    }

    void store(int position) {
        memory[position] = acc;
    }

    void add(int position) {
        acc += memory[position];
    }

    void subtract(int position) {
        acc -= memory[position];
    }

    void multiply(int position) {
        acc *= memory[position];
    }

    void divide(int position) {
        if (memory[position] != 0) {
            acc /= memory[position];
        }
    }

    public static void main(String[] args) {

    }
}
