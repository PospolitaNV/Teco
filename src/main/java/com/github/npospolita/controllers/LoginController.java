package com.github.npospolita.controllers;

import com.github.npospolita.TecoApplication;
import com.github.npospolita.model.User;
import com.github.npospolita.model.repo.UserRepository;
import com.github.npospolita.utils.JavaFxUtils;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalTime;

/**
 * Login controller.
 *
 * @author NPospolita
 * @since 03.12.2017
 */
@Component
public class LoginController {

    private final UserRepository userRepository;
    private final User currentUser;
    private final JavaFxUtils javaFxUtils;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    public TextField usernameField;
    public PasswordField passwordField;
    public Button submitButton;

    @Autowired
    public LoginController(UserRepository userRepository,
                           User currentUser,
                           JavaFxUtils javaFxUtils) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
        this.javaFxUtils = javaFxUtils;
    }

    public void checkLoginExistance(KeyEvent keyEvent) {
        if (userRepository.exists(usernameField.getText())) {
            logger.info("user found: " + usernameField.getText());
        }
    }

    public void submitButtonPressed(ActionEvent actionEvent) {
        if (userRepository.exists(usernameField.getText())) {
            checkPassword(passwordField);
            currentUser.setLogin(usernameField.getText());
            logger.info("user: \"" + usernameField.getText() + "\" logged in: " + LocalTime.now());
            switchStageToNotes();
        } else {
            currentUser.setLogin("");
        }
        logger.info("CURRENT USER: " + currentUser.getLogin());
    }


    private void switchStageToNotes() {
        Stage stage = TecoApplication.primaryStage;
        stage.setTitle("Menu");
        Pane myPane = null;
        try {
            myPane = javaFxUtils.getPane("/fxml/menu.fxml");
        } catch (IOException e) {
            logger.error("FUCK!", e);
        }
        Scene scene = new Scene(myPane);
        stage.setScene(scene);

        stage.show();
    }

    private void checkPassword(PasswordField passwordField) {
        //TODO first bug - no pwd check.
    }
}
