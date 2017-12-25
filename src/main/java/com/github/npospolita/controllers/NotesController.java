package com.github.npospolita.controllers;

import com.github.npospolita.UserDetails;
import com.github.npospolita.model.Note;
import com.github.npospolita.model.repo.NoteRepository;
import com.github.npospolita.utils.JavaFxUtils;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Controller for creating and editing notes.
 *
 * @author NPospolita
 * @since 06.12.2017
 */
@Component
public class NotesController {

    private static final Logger logger = LoggerFactory.getLogger(NotesController.class);

    public AnchorPane notesPane;

    public Button createNoteButton;
    public Button deleteNoteButton;
    public Button editNoteButton;

    public ListView<Note> notesList;

    private JavaFxUtils javaFxUtils;
    private NoteRepository noteRepository;
    private UserDetails userDetails;
    private SingleNoteController singleNoteController;

    @Autowired
    public NotesController(JavaFxUtils javaFxUtils,
                           NoteRepository noteRepository,
                           UserDetails userDetails) {
        this.javaFxUtils = javaFxUtils;
        this.noteRepository = noteRepository;
        this.userDetails = userDetails;
    }


    public void deleteNoteButtonPressed(ActionEvent actionEvent) {
        noteRepository.delete(notesList.getFocusModel().getFocusedItem());
        notesList.getItems().remove(notesList.getFocusModel().getFocusedItem());
        notesList.refresh();
    }

    public void createNoteButtonPressed(ActionEvent actionEvent) {
        javaFxUtils.createNewStageWithScene(SingleNoteController.sceneName);
        //TODO BUG - need to inherit style!
    }

    public void notesListClicked(MouseEvent mouseEvent) {
        editNoteButton.setDisable(false);
        deleteNoteButton.setDisable(false);
    }

    public void editNoteButtonPressed(ActionEvent actionEvent) {
        javaFxUtils.createNewStageWithScene(SingleNoteController.sceneName);
        singleNoteController.editNode(notesList.getFocusModel().getFocusedItem());
    }

    public void reloadNotes() {
        notesList.getItems().setAll(noteRepository.findAllByUserLogin(userDetails.getCurrentUser().getLogin()));
    }

    @Autowired
    public void setSingleNoteController(SingleNoteController singleNoteController) {
        this.singleNoteController = singleNoteController;
    }
}
