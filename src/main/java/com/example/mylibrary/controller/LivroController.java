package com.example.mylibrary.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mylibrary.controller.dto.CadastroLivroDTO;
import com.example.mylibrary.controller.mappers.LivroMapper;
import com.example.mylibrary.model.GeneroLivro;
import com.example.mylibrary.model.Livro;
import com.example.mylibrary.service.LivroService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.mylibrary.controller.dto.ResultadoPesquisaLivroDTO;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController implements GenericController {

    private final LivroService service;
    private final LivroMapper mapper;

    @PostMapping
    public ResponseEntity<Livro> save(@RequestBody @Valid CadastroLivroDTO dto) {
        Livro livro = mapper.toEntity(dto);
        livro = service.save(livro);
        var uri = generateHeaderLocation(livro.getId());
        return ResponseEntity.created(uri).body(livro);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultadoPesquisaLivroDTO> get(@PathVariable("id") String id) {
        return service.get(UUID.fromString(id)).map(
                livro -> {
                    var dto = mapper.toDTO(livro);
                    return ResponseEntity.ok(dto);
                }

        ).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        return service.get(UUID.fromString(id)).map(livro -> {
            service.delete(livro);
            return ResponseEntity.noContent().build();
        }).orElseGet(() -> {
            return ResponseEntity.notFound().build();
        });
    }

    @GetMapping
    public ResponseEntity<List<ResultadoPesquisaLivroDTO>> search(
            @RequestParam(name = "isbn", required = false) String isbn,
            @RequestParam(name = "titulo", required = false) String titulo,
            @RequestParam(name = "genero", required = false) GeneroLivro genero,
            @RequestParam(name = "ano-publicacao", required = false) Integer anoPublicacao) {

        var result = service.search(isbn, titulo, genero, anoPublicacao)
                .stream().map(mapper::toDTO).collect(Collectors.toList());
        return ResponseEntity.ok(result);
    }

}