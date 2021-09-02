package com.rafdev.activities.simpletron;

import java.util.Scanner;

public class Simpletron {
    // Read and Write Operations
    private final int READ = 10;
    private final int WRITE = 11;

    // Load and Store Operations
    private final int LOAD = 20;
    private final int STORE = 21;

    // Arithmetics Operations
    private final int ADD = 30;
    private final int SUBTRACT = 31;
    private final int DIVIDE = 32;
    private final int MULTIPLY = 33;

    // Transfer of Control Operations
    private final int BRANCH = 40;
    private final int BRANCH_NEG = 41;
    private final int BRANCH_ZERO = 42;
    private final int HALT = 43;

    private final int MEMORY_SIZE = 100;

    private int accumulator;
    private int[] memory;
    private int operationCode;
    private int operand;
    private int instructionCounter;

    public Simpletron() {
        welcomeMessage();
        initialize();
    }

    public void startSimulator() {
        int userInstruction, memoryPointer = 0;
        Scanner sc = new Scanner(System.in);

        while (true) {
            if (memoryPointer == MEMORY_SIZE) {
                System.out.println("\nMemory is full!");
                break;
            }

            System.out.printf("%02d ? ", memoryPointer);
            userInstruction = sc.nextInt();

            if (userInstruction == -9999) {
                break;
            } else {
                memory[memoryPointer] = userInstruction;
                memoryPointer++;
            }
        }

        System.out.println("\n****\t Programa carregado               \t****");
        System.out.println("****\t Iniciando a execução do programa \t****\n");

        for (int code: memory) {
            if (code != 0) {
                load();
                executeAction(operationCode, operand);
            }
        }
    }

    private void welcomeMessage() {
        System.out.println("****\tBem vindo ao Simpletron!                           \t****");
        System.out.println("****\tPor favor insira uma instrução (ou data word)      \t****");
        System.out.println("****\tpor vez em seu programa. Eu vou digitar o número   \t****");
        System.out.println("****\to número de alocação e o ponto de interrogação (?).\t****");
        System.out.println("****\tEntão você digita a palavra para a alocação.       \t****");
        System.out.println("****\tDigite o número -9999 para parar indicar o fim do  \t****");
        System.out.println("****\tseu programa.                                      \t****\n");
    }

    private void initialize() {
        memory = new int[MEMORY_SIZE];
        instructionCounter = 0;
    }

    private void load() {
        operationCode = memory[instructionCounter] / 100;
        operand = memory[instructionCounter] % 100;
    }

    private void executeAction(int operation, int operands) {
        switch (operation) {
            case READ:
                Scanner sc = new Scanner(System.in);
                System.out.print("Enter a number : ");
                memory[operands] = sc.nextInt();
                break;
            case WRITE:
                System.out.printf("Operation result : %d", memory[operands]);
                break;
            case LOAD:
                accumulator = memory[operands];
                break;
            case STORE:
                memory[operands] = accumulator;
                break;
            case ADD:
                accumulator += memory[operands];
                break;
            case SUBTRACT:
                accumulator -= memory[operands];
                break;
            case DIVIDE:
                if (memory[operands] == 0) {
                    System.out.println("Cannot divide by zero");
                    System.exit(0);
                } else {
                    accumulator /= memory[operands];
                }
                break;
            case MULTIPLY:
                accumulator *= memory[operands];
                break;
            case BRANCH:
                instructionCounter = operands;
                break;
            case BRANCH_NEG:
                if (accumulator < 0) {
                    instructionCounter = operands;
                }
                break;
            case BRANCH_ZERO:
                if (accumulator == 0) {
                    instructionCounter = operands;
                }
                break;
            case HALT:
                showTheCore();
                System.out.println("\nEnd of the program, process terminated!");
                System.exit(0);
                break;
        }

        instructionCounter++;
    }

    private void showTheCore() {
        System.out.printf("\n\n%35s\n", "MEMORY");

        for (int i = 0; i < 10; i++)
        {
            System.out.printf ("%6d", i);
        }

        System.out.println();
        int counter = 0;

        for (int i = 0; i < 10; i++)
        {
            if (counter % 10 == 0)
                System.out.printf ("%2d ", counter);

            for (int j = 0; j < 10; j++) {
                if (memory[counter] == 0) {
                    System.out.print( "+0000 ");
                } else {
                    System.out.printf("+%04d ", memory[counter]);
                }

                counter++;
            }

            System.out.println();
        }
    }
}
