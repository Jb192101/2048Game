package org.jedi_bachelor.view;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MainView extends GridPane {
    private static final int SIZE = 4; // 4x4 grid
    private static final int TILE_SIZE = 100;
    private static final int GAP = 10;
    private static final Color EMPTY_COLOR = Color.rgb(205, 193, 180);
    private static final Color TEXT_COLOR = Color.rgb(119, 110, 101);

    public MainView() {
        initGrid();
        renderEmptyGrid();
    }

    private void initGrid() {
        this.setHgap(GAP);
        this.setVgap(GAP);
        this.setStyle("-fx-background-color: #bbada0; -fx-padding: 10;");
    }

    private void renderEmptyGrid() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                addTile(0, row, col);
            }
        }
    }

    public void updateTile(int value, int row, int col) {
        this.getChildren().removeIf(node -> GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col);
        addTile(value, row, col);
    }

    private void addTile(int value, int row, int col) {
        StackPane tile = new StackPane();
        Rectangle bg = new Rectangle(TILE_SIZE, TILE_SIZE);

        if (value == 0) {
            bg.setFill(EMPTY_COLOR);
            tile.getChildren().add(bg);
        } else {
            bg.setFill(getTileColor(value));
            Text text = new Text(String.valueOf(value));
            text.setFont(Font.font("Arial", 32));
            text.setFill(TEXT_COLOR);
            tile.getChildren().addAll(bg, text);
        }

        this.add(tile, col, row);
    }

    private Color getTileColor(int value) {
        return switch (value) {
            case 2 -> Color.rgb(238, 228, 218);
            case 4 -> Color.rgb(237, 224, 200);
            case 8 -> Color.rgb(242, 177, 121);
            case 16 -> Color.rgb(245, 149, 99);
            case 32 -> Color.rgb(246, 124, 95);
            case 64 -> Color.rgb(246, 94, 59);
            case 128 -> Color.rgb(237, 207, 114);
            case 256 -> Color.rgb(237, 204, 97);
            case 512 -> Color.rgb(237, 200, 80);
            case 1024 -> Color.rgb(237, 197, 63);
            case 2048 -> Color.rgb(237, 194, 46);
            default -> Color.rgb(60, 58, 50);
        };
    }
}
