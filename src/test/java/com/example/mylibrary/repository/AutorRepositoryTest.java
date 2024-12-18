package com.example.mylibrary.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.mylibrary.model.Autor;

@DataJpaTest
public class AutorRepositoryTest {
    @Autowired
    private AutorRepository repository;

    @Test
    void givenNewCampaign_whenSave_thenSuccess() {
        // given
        Autor autor = new Autor();
        autor.setNome("Stephen");
        autor.setNacionalidade("Inglaterra");
        autor.setDataNascimento(LocalDate.of(1947, 9, 21));
        // when
       var r = repository.save(autor);
       
        // then
        assertNotNull(r.getId());
        assertEquals("Stephen", r.getNome());
        assertEquals("Inglaterra", r.getNacionalidade());
        assertEquals(LocalDate.of(1947, 9, 21), r.getDataNascimento());
    }
}
