package org.jedi_bachelor.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Deque;
import java.util.Random;
import java.util.ArrayDeque;

public class Game {
    @Getter
    private int[][] map;
    @Setter
    @Getter
    private int score;
    private final Random random;
    private byte countOfNules;

    public Game() {
        map = new int[][] { {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}};
        score = 0;
        random = new Random();
        countOfNules = 14;

        int i1 = random.nextInt(0, map.length);
        int j1 = random.nextInt(0, map.length);

        map[i1][j1] = 2;

        int i2;
        int j2;
        do {
            i2 = random.nextInt(0, map.length);
            j2 = random.nextInt(0, map.length);
        } while (i2 == i1 && j2 == j1);

        map[i2][j2] = 2;
    }

    public boolean isGameOver() {
        if (countOfNules > 0) return false;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (j < map[i].length - 1 && map[i][j] == map[i][j + 1]) {
                    return false;
                }
                if (i < map.length - 1 && map[i][j] == map[i + 1][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void moveLeft() {
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

        generateRandomTile();
    }

    public void moveRight() {
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

        generateRandomTile();
    }

    public void moveDown() {
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

        generateRandomTile();
    }

    public void moveUp() {
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

        generateRandomTile();
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
            return true;
        } else {
            return false;
        }
    }
}