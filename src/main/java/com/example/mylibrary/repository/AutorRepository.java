package com.example.mylibrary.repository;


import java.time.LocalDate;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.mylibrary.model.Autor;


// Extends CrudRepository tem todas as operacoes essenciais
public interface AutorRepository extends JpaRepository<Autor, UUID> {

    Autor findByNomeAndDataNascimentoAndNacionalidade(String nome, LocalDate dataNascimento, String nacionalidade);

    // List<Autor> search(String nome, String nacionalidade);

}
