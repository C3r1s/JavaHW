package com.example.androidhw3;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private char[][] board;
    private Button[][] buttons;
    private TextView gameStatusTextView;
    private RadioGroup difficultyRadioGroup;
    private Button newGameButton;

    private char currentPlayer;
    private boolean gameOver;
    private Difficulty currentDifficulty;

    private enum Difficulty {
        EASY, MEDIUM, HARD
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        board = new char[3][3];
        buttons = new Button[3][3];
        gameStatusTextView = findViewById(R.id.gameStatusTextView);
        difficultyRadioGroup = findViewById(R.id.difficultyRadioGroup);
        newGameButton = findViewById(R.id.newGameButton);

        GridLayout gameBoardGrid = findViewById(R.id.gameBoardGrid);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String buttonID = "button" + i + j;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this::onCellClicked);
            }
        }

        difficultyRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.radioEasy) {
                currentDifficulty = Difficulty.EASY;
            } else if (checkedId == R.id.radioMedium) {
                currentDifficulty = Difficulty.MEDIUM;
            } else if (checkedId == R.id.radioHard) {
                currentDifficulty = Difficulty.HARD;
            }
            Toast.makeText(MainActivity.this, "Сложность изменена на: " + currentDifficulty, Toast.LENGTH_SHORT).show();
            newGame();
        });

        currentDifficulty = Difficulty.EASY;
        ((RadioButton)findViewById(R.id.radioEasy)).setChecked(true);

        newGame();
    }

    private void newGame() {
        gameOver = false;
        currentPlayer = 'X';
        updateGameStatus("Ход игрока X");

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
                buttons[i][j].setBackgroundColor(Color.LTGRAY);
            }
        }
    }

    public void onCellClicked(View view) {
        if (gameOver || currentPlayer == 'O') {
            return;
        }

        Button clickedButton = (Button) view;
        String tag = (String) clickedButton.getTag();
        int row = Character.getNumericValue(tag.charAt(0));
        int col = Character.getNumericValue(tag.charAt(1));

        if (board[row][col] == ' ') {
            board[row][col] = currentPlayer;
            clickedButton.setText(String.valueOf(currentPlayer));
            clickedButton.setEnabled(false);
            checkGameEnd();
            if (!gameOver) {
                currentPlayer = 'O';
                updateGameStatus("Ход компьютера O");
                makeComputerMove();
            }
        } else {
            Toast.makeText(this, "Эта ячейка уже занята!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onNewGameClicked(View view) {
        newGame();
    }

    private void checkGameEnd() {
        if (checkWin()) {
            gameOver = true;
            updateGameStatus("Игрок " + currentPlayer + " победил!");
            highlightWinningCells();
            enableBoard(false);
        } else if (isBoardFull()) {
            gameOver = true;
            updateGameStatus("Ничья!");
            enableBoard(false);
        }
    }

    private boolean checkWin() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == currentPlayer && board[1][j] == currentPlayer && board[2][j] == currentPlayer) {
                return true;
            }
        }
        if ((board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer)) {
            return true;
        }
        return false;
    }

    private void highlightWinningCells() {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                buttons[i][0].setBackgroundColor(Color.GREEN);
                buttons[i][1].setBackgroundColor(Color.GREEN);
                buttons[i][2].setBackgroundColor(Color.GREEN);
                return;
            }
        }
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == currentPlayer && board[1][j] == currentPlayer && board[2][j] == currentPlayer) {
                buttons[0][j].setBackgroundColor(Color.GREEN);
                buttons[1][j].setBackgroundColor(Color.GREEN);
                buttons[2][j].setBackgroundColor(Color.GREEN);
                return;
            }
        }
        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            buttons[0][0].setBackgroundColor(Color.GREEN);
            buttons[1][1].setBackgroundColor(Color.GREEN);
            buttons[2][2].setBackgroundColor(Color.GREEN);
            return;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            buttons[0][2].setBackgroundColor(Color.GREEN);
            buttons[1][1].setBackgroundColor(Color.GREEN);
            buttons[2][0].setBackgroundColor(Color.GREEN);
            return;
        }
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private void updateGameStatus(String message) {
        gameStatusTextView.setText(message);
    }

    private void enableBoard(boolean enable) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setEnabled(enable && board[i][j] == ' ');
            }
        }
    }

    private void makeComputerMove() {
        if (gameOver) return;

        int[] move = null;
        switch (currentDifficulty) {
            case EASY:
                move = easyMove();
                break;
            case MEDIUM:
                move = mediumMove();
                break;
            case HARD:
                move = hardMove();
                break;
        }

        if (move != null) {
            int row = move[0];
            int col = move[1];
            board[row][col] = currentPlayer;
            buttons[row][col].setText(String.valueOf(currentPlayer));
            buttons[row][col].setEnabled(false);
            checkGameEnd();
            if (!gameOver) {
                currentPlayer = 'X';
                updateGameStatus("Ход игрока X");
            }
        }
    }

    private int[] easyMove() {
        List<int[]> emptyCells = getEmptyCells();
        if (!emptyCells.isEmpty()) {
            Random random = new Random();
            return emptyCells.get(random.nextInt(emptyCells.size()));
        }
        return null;
    }

    private int[] mediumMove() {
        int[] winningMove = findWinningMove(currentPlayer);
        if (winningMove != null) {
            return winningMove;
        }

        int[] blockingMove = findWinningMove('X');
        if (blockingMove != null) {
            return blockingMove;
        }

        if (board[1][1] == ' ') {
            return new int[]{1, 1};
        }

        int[][] corners = {{0, 0}, {0, 2}, {2, 0}, {2, 2}};
        for (int[] corner : corners) {
            if (board[corner[0]][corner[1]] == ' ') {
                return corner;
            }
        }

        return easyMove();
    }

    private int[] hardMove() {
        int[] winningMove = findWinningMove(currentPlayer);
        if (winningMove != null) {
            return winningMove;
        }

        int[] blockingMove = findWinningMove('X');
        if (blockingMove != null) {
            return blockingMove;
        }

        if (board[1][1] == ' ') {
            return new int[]{1, 1};
        }

        if (board[0][0] == 'X' && board[2][2] == ' ' && board[1][1] != 'O') return new int[]{2,2};
        if (board[0][2] == 'X' && board[2][0] == ' ' && board[1][1] != 'O') return new int[]{2,0};
        if (board[2][0] == 'X' && board[0][2] == ' ' && board[1][1] != 'O') return new int[]{0,2};
        if (board[2][2] == 'X' && board[0][0] == ' ' && board[1][1] != 'O') return new int[]{0,0};


        int[][] corners = {{0, 0}, {0, 2}, {2, 0}, {2, 2}};
        for (int[] corner : corners) {
            if (board[corner[0]][corner[1]] == ' ') {
                return corner;
            }
        }

        int[][] sides = {{0, 1}, {1, 0}, {1, 2}, {2, 1}};
        for (int[] side : sides) {
            if (board[side[0]][side[1]] == ' ') {
                return side;
            }
        }

        return easyMove();
    }

    private int[] findWinningMove(char player) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = player;
                    if (checkWinForPlayer(player)) {
                        board[i][j] = ' ';
                        return new int[]{i, j};
                    }
                    board[i][j] = ' ';
                }
            }
        }
        return null;
    }

    private boolean checkWinForPlayer(char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) return true;
        }
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player) return true;
        }
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player)) return true;
        return false;
    }

    private List<int[]> getEmptyCells() {
        List<int[]> emptyCells = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    emptyCells.add(new int[]{i, j});
                }
            }
        }
        return emptyCells;
    }
}