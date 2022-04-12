package project.app;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import project.data.Game;
import project.data.Timeline;
import project.data.Year;
import project.menu.Menu;
import project.menu.Output;

import java.util.ArrayList;

public class HelloController {
    @FXML private TextField homeFoulsInput;
    @FXML private TextField homeGoalsInput;
    @FXML private TextField homeShotsInput;
    @FXML private TextField homeTeamNameInput;
    @FXML private TextField awayFoulsInput;
    @FXML private TextField awayGoalsInput;
    @FXML private TextField awayShotsInput;
    @FXML private TextField awayTeamNameInput;
    @FXML private TextField gameDateInput;
    @FXML private TextField yearNumberInput;
    @FXML private TextField generalHomeInput;
    @FXML private TextField generalAwayInput;
    @FXML private TextField generalOutputYear;
    @FXML private TextField generalOutputMonth;
    @FXML private TextField generalOutputDay;
    @FXML private TextField generalYearInput;
    @FXML private TextArea display;
    @FXML public Label errorStatus;
    @FXML public Label displayStatus;

    /**
     * Adds a year to the data structure
     * @author Robert Engel AND Adrian Ponce, T03, April 9 2022
     */
    @FXML
    void addYearAction(MouseEvent event) {
        try{
            Timeline.addYear(Integer.parseInt(yearNumberInput.getText()));
            displayStatus.setText("Year added Successfully!");
        }
        catch(NumberFormatException e){
            errorStatus.setText("Error, Invalid Input! Please try Again!");
        }

    }
    /**
     * Adds a game to the data structure
     * @author Robert Engel, T03, April 9 2022
     */
    @FXML
    void addGameAction(MouseEvent event) {
        try{
            Game game = new Game(homeTeamNameInput.getText(),awayTeamNameInput.getText(),Integer.parseInt(homeFoulsInput.getText()),Integer.parseInt(awayFoulsInput.getText()),Integer.parseInt(homeShotsInput.getText()),Integer.parseInt(awayShotsInput.getText()),Integer.parseInt(homeGoalsInput.getText()),Integer.parseInt(awayGoalsInput.getText()),gameDateInput.getText());
            Game.addGameToYear(game, Integer.parseInt(gameDateInput.getText().substring(0, 4)));
            displayStatus.setText("Game Added successfully!");
        }
        catch(NumberFormatException e){
            errorStatus.setText("Error, Invalid Input! Please try Again!");
        }
    }
    @FXML
    void getAllYearsAction(MouseEvent event) {
        if (Timeline.getAllYears().isEmpty()) {
            errorStatus.setText("Error! No Year found!");
        }else
            displayStatus.setText("Got all years successfully!");
            display.setText(Timeline.getAllYears().toString());
    }
    @FXML
    void getAllGamesYearAction(MouseEvent event) {
        try {
            display.setText("All games for " + Integer.parseInt(generalYearInput.getText()) + " are:\n" + Timeline.getAllGameInAYear(Integer.parseInt(generalYearInput.getText())));
            displayStatus.setText("Got all games in year successfully!");
        }catch (Exception e){
            errorStatus.setText("Error! Invalid input! Please try again!");
        }
    }
    @FXML
    void getAllGamesEverAction(MouseEvent event) {
        if (Timeline.getAllGamesEverPlayed().isEmpty()) {
            errorStatus.setText("Error! No game Found!");
        } else
            displayStatus.setText("Got all the games ever played successfully!");
            display.setText(Timeline.getAllGamesEverPlayed().toString());
    }
    @FXML
    void getTopFoulsAction(MouseEvent event) {
        try {
            display.setText("The Top 5 amount of fouls in a game are:\n" + Output.getTopFiveFouls());
            displayStatus.setText("Got Top 5 fouls successfully!");
        } catch (Exception e) {
            errorStatus.setText("Error! Please try again!");
        }
    }
    @FXML
    void getTopGoalsAction(MouseEvent event) {
        try {
            display.setText("The Top 5 amount of goals in a game are:\n" + Output.getTopFiveGoals());
            displayStatus.setText("Got Top 5 goals successfully!");
        } catch (Exception e) {
            errorStatus.setText("Error! Please try again!");
        }
    }
    @FXML
    void getTopShotsAction(MouseEvent event) {
        try {
            display.setText("The Top 5 amount of shots in a game are:\n" + Output.getTopFiveShots());
            displayStatus.setText("Got Top 5 shots successfully!");
        } catch (Exception e) {
            errorStatus.setText("Error! Please try again!");
        }
    }
    @FXML
    void getTieGamesAction(MouseEvent event) {
        display.setText(Output.getGamesWithATie() + " Games ended in a tie");
        displayStatus.setText("Got Tie Games successfully!");
    }

    @FXML
    void getFoulsAction(MouseEvent event) {
        try {
            String date = Integer.parseInt(generalOutputYear.getText()) + "-" + Integer.parseInt(generalOutputMonth.getText()) + "-" + Integer.parseInt(generalOutputDay.getText());
            String gameID = generalHomeInput.getText() + " vs. " + generalAwayInput.getText() + " on " + date;
            display.setText("The game had " + Output.getFouls(Integer.parseInt(generalOutputYear.getText()), gameID) + " fouls");
            displayStatus.setText("Got Fouls Successfully!");
        } catch (NumberFormatException e) {
            errorStatus.setText("Error, Invalid Input! Please try Again!");
        }
    }

    @FXML
    void getShotsAction(MouseEvent event) {
        try {
            String date = Integer.parseInt(generalOutputYear.getText()) + "-" + Integer.parseInt(generalOutputMonth.getText()) + "-" + Integer.parseInt(generalOutputDay.getText());
            String gameID = generalHomeInput.getText() + " vs. " + generalAwayInput.getText() + " on " + date;
            display.setText("The game had " + Output.getShots(Integer.parseInt(generalOutputYear.getText()), gameID) + " shots");
            displayStatus.setText("Got Shots Successfully!");
        } catch (NumberFormatException e) {
            errorStatus.setText("Error, Invalid Input! Please try Again!");
        }
    }


    /**
     * General GUI setup stuff
     * @author Robert Engel, T03, April 9 2022
     * (I'm sure Adrian will add stuff here too)
     */
    @FXML
    public void initialize(){
        displayStatus.setTextFill(Paint.valueOf("GREEN"));
        displayStatus.setText("");
        errorStatus.setTextFill(Paint.valueOf("RED"));
        errorStatus.setText("");
        display.setText("");
    }
    /**
     * Saves the current data in the program to 'data.csv'
     * @author Robert Engel, T03, April 9 2022
     */
    @FXML
    private void onSaveClick(){
        int successCheck = project.menu.csvIo.createCsv("data");
        if(successCheck == 1){
            displayStatus.setText("File Saved Successfully!");
        }
        else{
            errorStatus.setText("Error! Something went wrong!");
        }
    }
    /**
     * Saves the current data in the program to the chosen filename
     * @author Robert Engel, T03, April 9 2022
     */
    @FXML
    private void onSaveAsClick(){
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        String filename = fileChooser.showSaveDialog(stage).getName();
        int successCheck = project.menu.csvIo.createCsv(filename);
        if(successCheck == 1){
            displayStatus.setText("File Saved Successfully!");
        }
        else{
            errorStatus.setText("Error! Something went wrong!");
        }
    }
    /**
     * Gives a short description and its creators (myself and Adrian)
     * @author Robert Engel, T03, April 9 2022
     */
    @FXML
    private void onAboutClick(){
        Text text = new Text();
        text.setText("Author: Robert Engel and Adrian Ponce\nVersion:" + Main.version + "\nThis is a Premier League Data Tracker");
        VBox vbox = new VBox(text);
        Scene scene = new Scene(vbox, 300, 50);
        Stage stage = new Stage();
        stage.setTitle("About");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * Loads a given CSV file, if it is of the correct type, it will load the data from the CSV into the program
     * @author Robert Engel, T03, April 9 2022
     */
    @FXML
    private void onLoadClick(){
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        String filename = fileChooser.showSaveDialog(stage).getName();
        int successCheck = project.menu.csvIo.loadCsv(filename);
        if(successCheck == 1){
            displayStatus.setText("File Loaded Successfully!");
        }
        else{
            errorStatus.setText("Error! Something went wrong!");
        }
    }

}