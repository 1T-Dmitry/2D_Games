package com.example.rockpaperscissors_game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

import java.util.Random;

public class GameController {
    @FXML
    private Button rockButton;

    @FXML
    private Button scissorsButton;

    @FXML
    private Button paperButton;

    @FXML
    private Label playerScoreLabel;

    @FXML
    private Label resultLabel;

    @FXML
    private Label computerScoreLabel;

    @FXML
    private ImageView playerChoiceImage;

    @FXML
    private ImageView computerChoiceImage;

    @FXML
    private HBox choicesHBox;

    private int playerScore = 0;
    private int computerScore = 0;
    private Random random = new Random();

    private static final int ROCK = 0;
    private static final int SCISSORS = 1;
    private static final int PAPER = 2;

    @FXML
    public void initialize() {
        loadImages();
        updateScore();

        rockButton.setOnAction(e -> playGame(ROCK));
        scissorsButton.setOnAction(e -> playGame(SCISSORS));
        paperButton.setOnAction(e -> playGame(PAPER));
    }

    private void loadImages() {
        try {
            playerChoiceImage.setImage(createColoredImage(Color.LIGHTGRAY));
            computerChoiceImage.setImage(createColoredImage(Color.LIGHTGRAY));
        } catch (Exception e) {
            System.out.println("Ошибка загрузки изображений: " + e.getMessage());
        }
    }

    private void playGame(int playerChoice) {
        int computerChoice = random.nextInt(3);

        showChoice(playerChoiceImage, playerChoice);
        showChoice(computerChoiceImage, computerChoice);

        determineWinner(playerChoice, computerChoice);

        updateScore();
    }

    private void updateScore() {
        playerScoreLabel.setText("Игрок: " + playerScore);
        computerScoreLabel.setText("Компьютер: " + computerScore);
    }

    private void determineWinner(int playerChoice, int computerChoice) {
        if (playerChoice == computerChoice) {
            resultLabel.setText("Ничья!");
            resultLabel.setTextFill(Color.BLUE);
        } else if ((playerChoice == ROCK && computerChoice == SCISSORS) ||
                    playerChoice == SCISSORS && computerChoice == PAPER ||
                    playerChoice == PAPER && computerChoice == ROCK) {
            resultLabel.setText("Вы выйграли!");
            resultLabel.setTextFill(Color.GREEN);
            playerScore++;
        } else {
            resultLabel.setText("Компьютер выйграл!");
            resultLabel.setTextFill(Color.RED);
            computerScore++;
        }
    }

    private Image createColoredImage(Color color) {
        Canvas canvas = new Canvas(100, 100);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(color);
        gc.fillRect(0, 0, 100, 100);

        return canvas.snapshot(null, null);
    }

    private void showChoice(ImageView imageView, int choice) {
        Color color;
        switch (choice) {
            case ROCK:
                color = Color.GRAY;
                break;
            case SCISSORS:
                color = Color.SILVER;
                break;
            case PAPER:
                color = Color.WHITE;
                break;
            default:
                color = Color.LIGHTGRAY;
        }
        imageView.setImage(createColoredImage(color));
    }

    @FXML
    private void onRockClicked() {
        playGame(ROCK);
    }

    @FXML
    private void onScissorsClicked() {
        playGame(SCISSORS);
    }

    @FXML
    private void onPaperClicked() {
        playGame(PAPER);
    }
}
