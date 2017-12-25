package com.github.npospolita.controllers;

import com.github.npospolita.UserDetails;
import com.github.npospolita.utils.JavaFxUtils;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Controller for menu.
 *
 * @author NPospolita
 * @since 03.12.2017
 */
@Component
public class MenuController {

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    private JavaFxUtils javaFxUtils;
    private final UserDetails userDetails;
    public Button exitButton;

    private Effect oldStyle;

    public AnchorPane settings;
    public AnchorPane notes;
    public AnchorPane parent;
    public Label notesLabel;
    public Label settingsLabel;
    private NotesController notesController;

    @Autowired
    public MenuController(JavaFxUtils javaFxUtils, UserDetails userDetails, NotesController notesController) {
        this.javaFxUtils = javaFxUtils;
        this.userDetails = userDetails;
        this.notesController = notesController;
    }

    public void notesClicked(MouseEvent mouseEvent) {
        settings.setVisible(false);
        notes.setVisible(true);
        notesController.reloadNotes();
    }

    public void settingsClicked(MouseEvent mouseEvent) {
        settings.setVisible(true);
        notes.setVisible(false);
    }


    public void mouseEnteredEffect(MouseEvent mouseEvent) {
        switch (((Label) mouseEvent.getSource()).getText()) {
            case "Notes":
                oldStyle = notesLabel.getEffect();
                notesLabel.setEffect(new Glow(1.0));
                break;
            case "Settings":
                oldStyle = settingsLabel.getEffect();
                settingsLabel.setEffect(new Glow(100.0));
                break;
        }
    }

    public void mouseExitedEffect(MouseEvent mouseEvent) {
        switch (((Label) mouseEvent.getSource()).getText()) {
            case "Notes":
                notesLabel.setEffect(oldStyle);
                break;
            case "Settings":
                settingsLabel.setEffect(oldStyle);
                break;
        }
    }

    public void exitButtonPressed(ActionEvent actionEvent) {
        userDetails.setCurrentUser(null);
        javaFxUtils.proceedToScene(LoginController.sceneName);
    }
}
