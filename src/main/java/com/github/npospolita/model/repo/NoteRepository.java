package com.github.npospolita.model.repo;

import com.github.npospolita.model.Note;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * INFO
 *
 * @author NPospolita
 * @since 06.12.2017
 */
public interface NoteRepository extends CrudRepository<Note, String> {

    public Set<Note> findAllByUserLogin(String login);

    public void deleteAllByUserLogin(String login);

}
