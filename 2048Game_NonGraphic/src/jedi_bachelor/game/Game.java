package jedi_bachelor.game;

/*
В данном случае объекты класса ArrayDeque работают как стэк
 */

import java.util.Deque;
import java.util.Random;
import java.util.ArrayDeque;
import java.util.Scanner;
import java.util.Arrays;

public class Game {
    private int[][] map;
    private int score;
    //private char prevMove;
    private final Random random;
    private byte countOfNules;

    public Game() {
        map = new int[][] { {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}};
        score = 0;
        //prevMove = 'q';
        random = new Random();
        countOfNules = 16;
    }

    public void run() {
        generateRandomTile();
        Scanner scan = new Scanner(System.in);
        while(generateRandomTile()) {
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
        }
        System.out.println("Game over!");
    }

    private void printMap() {
        for(int[] line : map) {
            System.out.println(Arrays.toString(line));
        }
        System.out.println("Score = " + this.score);
        System.out.println("Nules = " + this.countOfNules);
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
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < map.length; i++) {
            boolean isChanged = false;
            for(int j = 0; j < map.length; j++) {
                if(map[i][j] == 0) {
                    continue;
                } else if(stack.isEmpty()) {
                    stack.push(map[i][j]);
                } else if(stack.peek() != map[i][j] && isChanged) {
                    isChanged = false;
                    stack.push(map[i][j]);
                } else if(stack.peek() == map[i][j] && !isChanged) {
                    stack.pop();
                    stack.push(2*map[i][j]);
                    isChanged = true;
                    score += 2*map[i][j];
                    this.countOfNules++;
                } else {
                    stack.push(map[i][j]);
                }
                map[i][j] = 0;
            }

            // Возвращение элементов
            int size = stack.size();
            int k = 0;
            while(!stack.isEmpty()) {
                map[i][size - k - 1] = stack.pop();
                k++;
            }
        }

    }

    private void moveRigth() {
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < map.length; i++) {
            boolean isChanged = false;
            for(int j = map.length - 1; j >= 0; j--) {
                if(map[i][j] == 0) {
                    continue;
                } else if(stack.isEmpty()) {
                    stack.push(map[i][j]);
                } else if(stack.peek() != map[i][j] && isChanged) {
                    isChanged = false;
                    stack.push(map[i][j]);
                } else if(stack.peek() == map[i][j] && !isChanged) {
                    stack.pop();
                    stack.push(2*map[i][j]);
                    isChanged = true;
                    score += 2*map[i][j];
                    countOfNules++;
                } else {
                    stack.push(map[i][j]);
                }
                map[i][j] = 0;
            }

            // Возвращение элементов
            int k = map.length - stack.size();
            while(!stack.isEmpty()) {
                map[i][k] = stack.pop();
                k++;
            }
        }
    }

    private void moveDown() {
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < map.length; i++) {
            boolean isChanged = false;
            for(int j = map.length - 1; j >= 0; j--) {
                if(map[j][i] == 0) {
                    continue;
                } else if(stack.isEmpty()) {
                    stack.push(map[j][i]);
                } else if(stack.peek() != map[j][i] && isChanged) {
                    isChanged = false;
                    stack.push(map[j][i]);
                } else if(stack.peek() == map[j][i] && !isChanged) {
                    stack.pop();
                    stack.push(2*map[j][i]);
                    isChanged = true;
                    score += 2*map[j][i];
                    this.countOfNules++;
                } else {
                    stack.push(map[j][i]);
                }
                map[j][i] = 0;
            }

            // Возвращение элементов
            int k = map.length - stack.size();
            while(!stack.isEmpty()) {
                map[k][i] = stack.pop();
                k++;
            }
        }
    }

    private void moveUp() {
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < map.length; i++) {
            boolean isChanged = false;
            for(int j = map.length - 1; j >= 0; j--) {
                if(map[j][i] == 0) {
                    continue;
                } else if(stack.isEmpty()) {
                    stack.push(map[j][i]);
                } else if(stack.peek() != map[j][i] && isChanged) {
                    isChanged = false;
                    stack.push(map[j][i]);
                } else if(stack.peek() == map[j][i] && !isChanged) {
                    stack.pop();
                    stack.push(2*map[j][i]);
                    isChanged = true;
                    score += 2*map[j][i];
                    this.countOfNules++;
                } else {
                    stack.push(map[j][i]);
                }
                map[j][i] = 0;
            }

            // Возвращение элементов
            int size = stack.size();
            int k = size - 1;
            while(!stack.isEmpty()) {
                map[size - k - 1][i] = stack.pop();
                k--;
            }
        }
    }

    private boolean generateRandomTile() {
        if(this.countOfNules != 0) {
            int row = -1;
            int col = -1;
            do {
                row = random.nextInt(map.length);
                col = random.nextInt(map.length);
            } while (map[row][col] != 0);

            int value = (random.nextDouble() < 0.9) ? 2 : 4;

            map[row][col] = value;
            countOfNules--;
            return true; // Если есть нули
        } else {
            return false; // Если нет нулей
        }
    }

}
