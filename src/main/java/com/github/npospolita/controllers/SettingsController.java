package com.github.npospolita.controllers;

import com.github.npospolita.TecoApplication;
import com.github.npospolita.UserDetails;
import com.github.npospolita.model.repo.NoteRepository;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Controller for settings.
 *
 * @author NPospolita
 * @since 06.12.2017
 */
@Component
public class SettingsController {


    private MediaPlayer musicPlayer;
    public Slider sliderMusic;
    public ToggleButton themeToggle;
    public ColorPicker colorPicker;
    public Button clearNotesButton;
    public AnchorPane settingsPane;

    private NoteRepository noteRepository;
    private UserDetails userDetails;

    @Autowired
    public SettingsController(MediaPlayer musicPlayer, NoteRepository noteRepository, UserDetails userDetails) {
        this.musicPlayer = musicPlayer;
        this.noteRepository = noteRepository;
        this.userDetails = userDetails;
    }


    public void musicSliderMoved(MouseEvent mouseEvent) {
        if (musicPlayer.getVolume() > 0.99) {
            //TODO third bug - music can't be scaled after 99%
            return;
        }
        musicPlayer.setVolume(sliderMusic.getValue() / 100.0);
    }

    public void darkThemeSwitch(ActionEvent actionEvent) {
        //todo forth bug - just shit :D
        //skip it in documentation.
    }

    public void colorPickerChanged(ActionEvent actionEvent) {
        Color pickerValue = colorPicker.getValue();
        if (pickerValue.equals(Color.BLACK)
                || pickerValue.equals(Color.RED)
                || pickerValue.equals(Color.GREEN)
                || pickerValue.equals(Color.WHITE)) {
            //TODO second bug - some colors can't be chosen!
            return;
        }
        String webFormat = String.format("#%02x%02x%02x",
                (int) (255 * pickerValue.getRed()),
                (int) (255 * pickerValue.getGreen()),
                (int) (255 * pickerValue.getBlue()));
        TecoApplication.primaryStage.getScene().getRoot().setStyle("-fx-base: " + webFormat);
    }

    public void clearNotesButtonPressed(ActionEvent actionEvent) {
        noteRepository.delete(noteRepository.findAllByUserLogin(userDetails.getCurrentUser().getLogin()));
    }
}
