package com.github.npospolita.controllers;

import com.github.npospolita.UserDetails;
import com.github.npospolita.model.Note;
import com.github.npospolita.model.repo.NoteRepository;
import com.github.npospolita.utils.JavaFxUtils;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * INFO
 *
 * @author NPospolita
 * @since 06.12.2017
 */
@Component
public class SingleNoteController {

    public static String sceneName = "newNote";

    public TextField titleText;
    public TextArea bodyText;
    public Button saveNoteButton;
    public Button cancelButton;

    private JavaFxUtils javaFxUtils;
    private NoteRepository noteRepository;
    private NotesController notesController;
    private UserDetails userDetails;
    private boolean isEdit;
    private Note editableNote;

    @Autowired
    public SingleNoteController(JavaFxUtils javaFxUtils,
                                NoteRepository noteRepository,
                                UserDetails userDetails) {
        this.javaFxUtils = javaFxUtils;
        this.noteRepository = noteRepository;
        this.userDetails = userDetails;
    }

    public void saveNoteButtonPressed(ActionEvent actionEvent) {
        if (isEdit) {
            editableNote.setTitle(titleText.getText());
            editableNote.setBody(bodyText.getText());
            noteRepository.save(editableNote);
            notesController.notesList.refresh();
        } else {
            Note note = new Note();
            note.setBody(bodyText.getText());
            note.setTitle(titleText.getText());
            note.setUser(userDetails.getCurrentUser());
            noteRepository.save(note);
            notesController.notesList.getItems().add(note);
        }
        javaFxUtils.closeStage(sceneName);
        isEdit = false;
    }

    public void cancelButtonPressed(ActionEvent actionEvent) {
        javaFxUtils.closeStage(sceneName);
        isEdit = false;
    }

    public void editNode(Note note) {
        bodyText.setText(note.getBody());
        titleText.setText(note.getTitle());
        isEdit = true;
        editableNote = note;
    }

    @Autowired
    public void setNotesController(NotesController notesController) {
        this.notesController = notesController;
    }
}
