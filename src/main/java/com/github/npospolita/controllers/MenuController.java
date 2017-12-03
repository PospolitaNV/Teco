package com.github.npospolita.controllers;

import com.github.npospolita.model.User;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * INFO
 *
 * @author NPospolita
 * @since 03.12.2017
 */
@Component
public class MenuController {

    private static final Logger logger = LoggerFactory.getLogger(MenuController.class);

    private final User currentUser;

    public AnchorPane settings;
    public AnchorPane notes;
    public Slider slider;
    public ToggleButton themeToggle;
    public ColorPicker colorPicker;
    public Button clearNotesButton;


    @Autowired
    public MenuController(User currentUser) {
        this.currentUser = currentUser;
    }

    public void notesClicked(MouseEvent mouseEvent) {
        settings.setVisible(false);
        notes.setVisible(true);
    }

    public void settingsClicked(MouseEvent mouseEvent) {
        settings.setVisible(true);
        notes.setVisible(false);
    }


}
