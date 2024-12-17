package com.example.mylibrary.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.mylibrary.controller.dto.AutorDTO;
import com.example.mylibrary.model.Autor;
import com.example.mylibrary.security.SecurityService;
import com.example.mylibrary.service.AutorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.mylibrary.controller.mappers.AutorMapper;

@RestController
@RequestMapping("/autores")
@RequiredArgsConstructor
public class AutorController implements GenericController {

    private final AutorService service;
    private final AutorMapper mapper;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<Autor> salvar(@RequestBody @Valid AutorDTO dto) {
    // public ResponseEntity<Autor> salvar(@RequestBody @Valid AutorDTO dto, Authentication auth) {
        // var entity = autor.mapearParaAutor();
        // UserDetails user = (UserDetails) auth.getPrincipal();
        //pode pegar o principal e injetar como quiser
        var entity = mapper.toEntity(dto);
        var result = service.salvar(entity);
        var uri = generateHeaderLocation(result.getId());
        return ResponseEntity.created(uri).body(result);
    }

    @GetMapping("{id}")
    public ResponseEntity<Autor> getById(@PathVariable("id") String id) {
        var autor = service.getById(id);
        return autor != null ? ResponseEntity.ok(autor) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<AutorDTO>> search(@RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "nacionalidade", required = false) String nacionalidade) {
        var result = service.search(nome, nacionalidade).stream().map(autor -> new AutorDTO(autor.getId(),
                autor.getNome(),
                autor.getDataNascimento(),
                autor.getNacionalidade())).collect(Collectors.toList());

        return ResponseEntity.ok(result);

    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody @Valid AutorDTO autor) {
        Autor result = service.getById(id);
        if (result == null) {
            return ResponseEntity.notFound().build();
        }
        result.setNome(autor.nome());
        result.setDataNascimento(autor.dataNascimento());
        result.setNacionalidade(autor.nacionalidade());
        return ResponseEntity.ok(service.update(result));
    }

}
