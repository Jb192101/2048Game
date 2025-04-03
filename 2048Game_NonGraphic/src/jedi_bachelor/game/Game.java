package jedi_bachelor.game;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Game {
    private int[][] map;
    private int score;
    //private char prevMove;
    private final Random random;

    public Game() {
//        map = new int[][] { {0, 0, 0, 0},
//                            {0, 0, 0, 0},
//                            {0, 0, 0, 0},
//                            {0, 0, 0, 0}};
        map = new int[][] { {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}};
        score = 0;
        //prevMove = 'q';
        random = new Random();
    }

    public void run() {
        generateRandomTile();
        generateRandomTile();
        Scanner scan = new Scanner(System.in);
        while(true) {
            printMap();
            printMenu();
            //char n = prevMove;
            //do {
            System.out.print("Куда идти: ");
            char n = scan.next().charAt(0);
            //} while(n == prevMove);

            switch (n) {
                case 'w':
                    moveUp();
                    //prevMove = 'w';
                    break;
                case 'd':
                    moveRigth();
                    //prevMove = 'd';
                    break;
                case 's':
                    moveDown();
                    //prevMove = 's';
                    break;
                case 'a':
                    moveLeft();
                    //prevMove = 'a';
                    break;
                default:
                    System.out.println("Incorrect input value!");
            }
            generateRandomTile();
        }
    }

    private void printMap() {
        for(int[] line : map) {
            System.out.println(Arrays.toString(line));
        }
        System.out.println("Score = " + this.score);
    }

    private void printMenu() {
        System.out.println();
        System.out.println("w - Вверх");
        System.out.println("d - Вправо");
        System.out.println("s - Вниз");
        System.out.println("a - Влево");
        System.out.println();
    }

    // move-ы
    private void moveLeft() {
        for(int i = 0; i < map.length; i++) {
            Stack<Integer> stack = new Stack<>();
            boolean isChanged = false;
            for(int j = 0; j < map.length; j++) {
                if(map[i][j] == 0) {
                    continue;
                } else if(stack.empty()) {
                    stack.push(map[i][j]);
                } else if(stack.peek() == map[i][j] && !isChanged) {
                    stack.pop();
                    stack.push(2*map[i][j]);
                    isChanged = true;
                    score += 2*map[i][j];
                } else {
                    stack.push(map[i][j]);
                }
                map[i][j] = 0;
            }

            // Возвращение элементов
            int size = stack.size();
            int k = 0;
            while(!stack.empty()) {
                map[i][size - k - 1] = stack.pop();
                k++;
            }
        }

    }

    private void moveRigth() {
        for(int i = 0; i < map.length; i++) {
            Stack<Integer> stack = new Stack<>();
            boolean isChanged = false;
            for(int j = map.length - 1; j >= 0; j--) {
                if(map[i][j] == 0) {
                    continue;
                } else if(stack.empty()) {
                    stack.push(map[i][j]);
                } else if(stack.peek() == map[i][j] && !isChanged) {
                    stack.pop();
                    stack.push(2*map[i][j]);
                    isChanged = true;
                    score += 2*map[i][j];
                } else {
                    stack.push(map[i][j]);
                }
                map[i][j] = 0;
            }

            // Возвращение элементов
            int k = map.length - stack.size();
            while(!stack.empty()) {
                map[i][k] = stack.pop();
                k++;
            }
        }
    }

    private void moveDown() {
        for(int i = 0; i < map.length; i++) {
            Stack<Integer> stack = new Stack<>();
            boolean isChanged = false;
            for(int j = map.length - 1; j >= 0; j--) {
                if(map[j][i] == 0) {
                    continue;
                } else if(stack.empty()) {
                    stack.push(map[j][i]);
                } else if(stack.peek() == map[j][i] && !isChanged) {
                    stack.pop();
                    stack.push(2*map[j][i]);
                    isChanged = true;
                    score += 2*map[j][i];
                } else {
                    stack.push(map[j][i]);
                }
                map[j][i] = 0;
            }

            // Возвращение элементов
            int k = map.length - stack.size();
            while(!stack.empty()) {
                map[k][i] = stack.pop();
                k++;
            }
        }
    }

    private void moveUp() {
        for(int i = 0; i < map.length; i++) {
            Stack<Integer> stack = new Stack<>();
            boolean isChanged = false;
            for(int j = map.length - 1; j >= 0; j--) {
                if(map[j][i] == 0) {
                    continue;
                } else if(stack.empty()) {
                    stack.push(map[j][i]);
                } else if(stack.peek() == map[j][i] && !isChanged) {
                    stack.pop();
                    stack.push(2*map[j][i]);
                    isChanged = true;
                    score += 2*map[j][i];
                } else {
                    stack.push(map[j][i]);
                }
                map[j][i] = 0;
            }

            // Возвращение элементов
            int size = stack.size();
            int k = 0;
            while(!stack.empty()) {
                map[size - k - 1][i] = stack.pop();
                k++;
            }
        }
    }

    private void generateRandomTile() {
        int row = -1;
        int col = -1;
        do {
            row = random.nextInt(4);
            col = random.nextInt(4);
        } while(map[row][col] != 0);

        int value = (random.nextDouble() < 0.9) ? 2 : 4;

        map[row][col] = value;
    }

}
