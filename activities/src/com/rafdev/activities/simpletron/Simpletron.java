package com.rafdev.activities.simpletron;

import java.util.Scanner;

public class Simpletron {
    private final int MEMORY_SIZE = 100;

    private int accumulator;
    private int[] memory;
    private int instructionCounter;

    public Simpletron() {
        initialize();
        welcomeMessage();
    }

    public void runSimulator() {
        int input, memoryPointer = 0;
        int operationCode, operand;
        Scanner sc = new Scanner(System.in);

        while (true) {
            if (memoryPointer == MEMORY_SIZE) {
                break;
            }

            do {
                System.out.printf("[%02d]: ? ", memoryPointer);
                input = sc.nextInt();

                if (input >= -9998 && input <= 999) {
                    System.out.println("Wrong input, try again.");
                }

            } while (input >= -9998 && input <= 999);


            if (input == -9999) {
                break;
            }

            memory[memoryPointer] = input;
            memoryPointer++;
        }

        System.out.println("\n****\t Programa carregado               \t****");
        System.out.println("****\t Iniciando a execução do programa \t****\n");

        for (int code : memory) {
            if (code != 0) {
                operationCode = memory[instructionCounter] / 100;
                operand = memory[instructionCounter] % 100;
                executeAction(operationCode, operand);
            }
        }
    }

    private void initialize() {
        memory = new int[MEMORY_SIZE];
        instructionCounter = 0;
    }

    private void welcomeMessage() {
        System.out.println("\n****\tBem vindo ao Simpletron!                           \t****");
        System.out.println("****\tPor favor insira uma instrução (ou data word)      \t****");
        System.out.println("****\tpor vez em seu programa. Eu vou digitar o número   \t****");
        System.out.println("****\to número de alocação e o ponto de interrogação (?).\t****");
        System.out.println("****\tEntão você digita a palavra para a alocação.       \t****");
        System.out.println("****\tDigite o número -9999 para parar indicar o fim do  \t****");
        System.out.println("****\tseu programa.                                      \t****\n");
    }

    private void executeAction(int operation, int operands) {
        // Read and Write Operations
        final int READ = 10;
        final int WRITE = 11;

        // Load and Store Operations
        final int LOAD = 20;
        final int STORE = 21;

        // Arithmetics Operations
        final int ADD = 30;
        final int SUBTRACT = 31;
        final int DIVIDE = 32;
        final int MULTIPLY = 33;

        // Transfer of Control Operations
        final int BRANCH = 40;
        final int BRANCH_NEG = 41;
        final int BRANCH_ZERO = 42;
        final int HALT = 43;

        switch (operation) {
            case READ -> read(operands);
            case WRITE -> write(operands);
            case LOAD -> load(operands);
            case STORE -> store(operands);
            case ADD -> add(operands);
            case SUBTRACT -> subtract(operands);
            case DIVIDE -> divide(operands);
            case MULTIPLY -> multiply(operands);
            case BRANCH -> branch(operands);
            case BRANCH_NEG -> branchNeg(operands);
            case BRANCH_ZERO -> branchZero(operands);
            case HALT -> halt();
        }

        instructionCounter++;
        accumulator = memory[instructionCounter];
    }

    private void read(int position) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("[%02d]: ", position);
        memory[position] = sc.nextInt();
    }

    private void write(int position) {
        System.out.printf("Data [%02d] = %04d", position, memory[position]);
    }

    private void load(int position) {
        accumulator = memory[position];
    }

    private void store(int position) {
        memory[position] = accumulator;
    }

    private void add(int position) {
        accumulator += memory[position];
    }

    private void subtract(int position) {
        accumulator -= memory[position];
    }

    private void multiply(int position) {
        accumulator *= memory[position];
    }

    private void branch(int position) {
        instructionCounter = position;
    }

    private void branchNeg(int position) {
        if (accumulator < 0) {
            instructionCounter = position;
        }
    }

    private void branchZero(int position) {
        if (accumulator == 0) {
            instructionCounter = position;
        }
    }

    private void divide(int position) {
        if (memory[position] == 0) {
            System.exit(0);
        } else {
            accumulator /= memory[position];
        }
    }

    private void halt() {
        showTheCore();
        System.out.println("\nEnd of the program, process terminated!");
        System.exit(0);
    }

    private void showTheCore() {
        System.out.println("\n");

        for (int i = 0; i < 10; i++) {
            System.out.printf("\t%7d", i);
        }

        System.out.println("\n");
        int counter = 0;

        for (int i = 0; i < 10; i++) {
            if (counter % 10 == 0)
                System.out.printf("%2d\t\t", counter);

            for (int j = 0; j < 10; j++) {
                if (memory[counter] == 0) {
                    System.out.print("+____\t");
                } else {
                    System.out.printf("+%04d\t", memory[counter]);
                }

                counter++;
            }

            System.out.println();
        }
    }
}
