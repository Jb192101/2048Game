package org.jedi_bachelor.model;

import lombok.Setter;

import java.util.Deque;
import java.util.Random;
import java.util.ArrayDeque;
import java.util.Scanner;

public class Game {
    private int[][] map;
    @Setter
    private int score;
    private final Random random;
    private byte countOfNules;
    @Setter
    private GameUpdateCallback callback;

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
            System.out.print("Куда идти: ");
            char n = scan.next().charAt(0);

            switch (n) {
                case 'w':
                    moveUp();
                    break;
                case 'd':
                    moveRight();
                    break;
                case 's':
                    moveDown();
                    break;
                case 'a':
                    moveLeft();
                    break;
                default:
                    System.out.println("Incorrect input value!");
            }
        }
        System.out.println("Game over!");
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

            int size = stack.size();
            int k = 0;
            while(!stack.isEmpty()) {
                map[i][size - k - 1] = stack.pop();
                k++;
            }
        }

    }

    private void moveRight() {
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

    private void notifyMapUpdated() {
        if (callback != null) {
            callback.onMapUpdated(this.map);
        }
    }

    // Интерфейс для callback-ов
    public interface GameUpdateCallback {
        void onMapUpdated(int[][] newMap);
        void onScoreUpdated(int newScore);
        void onGameOver();
    }
}