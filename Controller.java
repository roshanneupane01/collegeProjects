package sample;


import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;



public class Controller {

    public static final String ANSI_RED = "\u001B[31m";
    public HBox appRadioButton;
    public ComboBox<String> musicComboBox;
    public Text typeOfMusic;
    public Text typeOfApp;
    public CheckBox app;
    public CheckBox music;
    public TextField name;
    public TextField street;
    public TextField city;
    public TextField state;
    public TextField zip;
    public TextField enterTitle;
    public TextField datePurchased;
    public TextField accountNumber;
    public Button submit;
    public Button finish;
    public ArrayList<String> customerArrayApp = new ArrayList<>();
    public ArrayList<String> customerArrayMusic = new ArrayList<>();
    public String[] customerArrayInfoHolder;
    public RadioButton radioButtonGame;
    public RadioButton radioButtonProductivity;
    public RadioButton radioButtonEducation;
    public ToggleGroup appToggleGroup;
    public String rocknroll;
    public String alternative;
    public String chooseOne;


    @FXML
    public void selectAppOrMusic() {
        if (app.isSelected()) {
            musicComboBox.setDisable(true);
            music.setDisable(true);
            typeOfMusic.opacityProperty().bind(
                    Bindings.when(
                            music.disabledProperty())
                            .then(0.3)
                            .otherwise(1));
        } else if (music.isSelected()) {
            appRadioButton.setDisable(true);
            app.setDisable(true);
            typeOfApp.opacityProperty().bind(Bindings.when(
                    app.disabledProperty())
                    .then(0.3)
                    .otherwise(1));
        } else {
            appRadioButton.setDisable(false);
            app.setDisable(false);
            musicComboBox.setDisable(false);
            music.setDisable(false);
        }
    }

    public void emptyField(ActionEvent e) {
        if (e.getSource().equals(submit)) {
            if (name.getText().isBlank()) {
                name.requestFocus();
                name.setPromptText("Please enter a name");
                System.out.println(ANSI_RED + "Please enter a name");
            }
            if (street.getText().isBlank()) {
                street.requestFocus();
                street.setPromptText("Please enter a street");
                System.out.println(ANSI_RED + "Please enter a street");
            }
            if (city.getText().isBlank()) {
                city.requestFocus();
                city.setPromptText("Please enter a city");
                System.out.println(ANSI_RED + "Please enter a city");
            }
            if (state.getText().isBlank()) {
                state.requestFocus();
                state.setPromptText("Please enter a state");
                System.out.println(ANSI_RED + "Please enter a state");
            }
            if (zip.getText().isBlank()) {
                zip.requestFocus();
                zip.setPromptText("Please enter a zip");
                System.out.println(ANSI_RED + "Please enter a zip");
            }
            if (!app.isSelected() && !music.isSelected()) {
                System.out.println("Please select either app or music");
            }
            if (app.isSelected() && (!radioButtonGame.isSelected() && !radioButtonProductivity.isSelected() && !radioButtonEducation.isSelected())) {
                System.out.println("Please select one of the options from 'Type of App'");
            }
            if(music.isSelected() && musicComboBox.getSelectionModel().isSelected(0)){
                System.out.println("Please select one of the music genres from 'Type of Music'");
            }
            if (enterTitle.getText().isBlank()) {
                enterTitle.requestFocus();
                enterTitle.setPromptText("Please enter a title");
                System.out.println(ANSI_RED + "Please enter a Title");
            }
            if (datePurchased.getText().isBlank()) {
                datePurchased.requestFocus();
                datePurchased.setPromptText("Please enter a date of purchase");
                System.out.println(ANSI_RED + "Please enter Date Purchased");
            }
            if (name.getText().isBlank() && accountNumber.getText().isBlank()) {
                name.requestFocus();
                accountNumber.setPromptText("Please enter an account number");
                System.out.println(ANSI_RED + "Please enter Account Number");
            } else if (accountNumber.getText().isBlank()) {
                accountNumber.requestFocus();
                accountNumber.setPromptText("Please enter an account number");
                System.out.println(ANSI_RED + "Please enter Account Number");
            }


            if (!name.getText().isBlank() && !street.getText().isBlank() && !city.getText().isBlank() &&
                    !state.getText().isBlank() && !zip.getText().isBlank() && ((app.isSelected() &&
                    (radioButtonGame.isSelected() || radioButtonProductivity.isSelected() || radioButtonEducation.isSelected())) ||
                    (music.isSelected() && (musicComboBox.getSelectionModel().isSelected(1) || musicComboBox.getSelectionModel().isSelected(2)) && !musicComboBox.getSelectionModel().isSelected(0))) && !enterTitle.getText().isBlank() &&
                    !datePurchased.getText().isBlank() && !accountNumber.getText().isBlank()) {
                if (app.isSelected()) {
                    if (!radioButtonGame.isSelected() && !radioButtonProductivity.isSelected() && !radioButtonEducation.isSelected()) {
                        System.out.println(ANSI_RED + "Please select a 'Type of App'");
                    }
                    if (radioButtonGame.isSelected()) {
                        customerArrayInfoHolder = new String[]{name.getText(), street.getText(), city.getText(), state.getText(),
                                zip.getText(), radioButtonGame.getText(), enterTitle.getText(), datePurchased.getText(), accountNumber.getText()};
                        for (String s : customerArrayInfoHolder) {
                            System.out.println(s);
                        }
                    }
                    if (radioButtonProductivity.isSelected()) {
                        customerArrayInfoHolder = new String[]{name.getText(), street.getText(), city.getText(), state.getText(),
                                zip.getText(), radioButtonProductivity.getText(), enterTitle.getText(), datePurchased.getText(), accountNumber.getText()};
                        for (String s : customerArrayInfoHolder) {
                            System.out.println(s);
                        }
                    }
                    if (radioButtonEducation.isSelected()) {
                        customerArrayInfoHolder = new String[]{name.getText(), street.getText(), city.getText(), state.getText(),
                                zip.getText(), radioButtonEducation.getText(), enterTitle.getText(), datePurchased.getText(), accountNumber.getText()};
                        for (String s : customerArrayInfoHolder) {
                            System.out.println(s);
                        }
                    }
                    customerArrayApp.add(Arrays.toString(customerArrayInfoHolder));
                    if(e.getSource().equals(submit)){
                        try (BufferedWriter bufferedWriter = new BufferedWriter(new PrintWriter("app.txt"))) {
                            bufferedWriter.write(String.valueOf(customerArrayApp));
                            bufferedWriter.newLine();
                        } catch (IOException exception) {
                            exception.printStackTrace();
                        }
                    }

                } else if (music.isSelected()) {
                    if (musicComboBox.getSelectionModel().isSelected(1)) {
                        customerArrayInfoHolder = new String[]{name.getText(), street.getText(), city.getText(), state.getText(),
                                zip.getText(), rocknroll, enterTitle.getText(), datePurchased.getText(), accountNumber.getText()};
                        for (String s : customerArrayInfoHolder) {
                            System.out.println(s);
                        }
                    } else if (musicComboBox.getSelectionModel().isSelected(2)) {
                        customerArrayInfoHolder = new String[]{name.getText(), street.getText(), city.getText(), state.getText(),
                                zip.getText(), alternative, enterTitle.getText(), datePurchased.getText(), accountNumber.getText()};
                        for (String s : customerArrayInfoHolder) {
                            System.out.println(s);
                        }
                    } else{
                        System.out.println("Please select on the music genres from 'Type of Music'");
                    }
                    customerArrayMusic.add(Arrays.toString(customerArrayInfoHolder));
                        try (BufferedWriter bufferedWriter = new BufferedWriter(new PrintWriter("music.txt"))) {
                            bufferedWriter.write(customerArrayMusic.toString());
                            bufferedWriter.newLine();
                        } catch (IOException exception) {
                            exception.printStackTrace();
                        }

                }

                if (e.getSource().equals(submit)) {
                    name.clear();
                    street.clear();
                    city.clear();
                    state.clear();
                    zip.clear();
                    app.setSelected(false);
                    app.setDisable(false);
                    music.setSelected(false);
                    music.setDisable(false);
                    musicComboBox.getSelectionModel().select(chooseOne);
                    musicComboBox.setDisable(false);
                    appRadioButton.setDisable(false);
                    radioButtonGame.setSelected(false);
                    radioButtonProductivity.setSelected(false);
                    radioButtonEducation.setSelected(false);
                    enterTitle.clear();
                    datePurchased.clear();
                    accountNumber.clear();
                    name.requestFocus();
                }
            }

        } else if (e.getSource().equals(finish)) {
            if (customerArrayInfoHolder != null){
                Platform.exit();
            }
        }
    }
}

