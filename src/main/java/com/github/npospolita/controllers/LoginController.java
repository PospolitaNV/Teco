package com.github.npospolita.controllers;

import com.github.npospolita.UserDetails;
import com.github.npospolita.model.repo.UserRepository;
import com.github.npospolita.utils.JavaFxUtils;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

/**
 * Login controller.
 *
 * @author NPospolita
 * @since 03.12.2017
 */
@Component
public class LoginController {

    public static final String sceneName = "login";
    private final UserRepository userRepository;
    private final UserDetails userDetails;
    private final JavaFxUtils javaFxUtils;
    private final MenuController menuController;

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    public TextField usernameField;
    public PasswordField passwordField;
    public Button submitButton;
    public Label passwordErrorLabel;
    public Label usernameErrorLabel;

    @Autowired
    public LoginController(UserRepository userRepository,
                           UserDetails userDetails,
                           JavaFxUtils javaFxUtils, MenuController menuController) {
        this.userRepository = userRepository;
        this.userDetails = userDetails;
        this.javaFxUtils = javaFxUtils;
        this.menuController = menuController;
    }

    public void checkLoginExistance(KeyEvent keyEvent) {
        if (userRepository.exists(usernameField.getText())) {
            logger.info("user found: " + usernameField.getText());
        }
        usernameErrorLabel.setText("");
    }

    public void submitButtonPressed(ActionEvent actionEvent) {
        if (userRepository.exists(usernameField.getText())) {
            checkPassword(passwordField);
            userDetails.setCurrentUser(userRepository.findOne(usernameField.getText()));
            logger.info("user: \"" + usernameField.getText() + "\" logged in: " + LocalTime.now());
            switchStageToNotes();
        } else {
            usernameErrorLabel.setText("User with name \"" + usernameField.getText() + "\" is not found!");
            userDetails.setCurrentUser(null);
        }
        logger.info("CURRENT USER: " + userDetails.getCurrentUser());
    }


    private void switchStageToNotes() {
        javaFxUtils.proceedToScene("menu");
        menuController.notes.setVisible(false);
        menuController.settings.setVisible(false);
    }

    private void checkPassword(PasswordField passwordField) {
        //TODO first bug - no pwd check.
    }
}
