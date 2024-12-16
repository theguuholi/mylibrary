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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.mylibrary.controller.dto.ResultadoPesquisaLivroDTO;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/livros")
@RequiredArgsConstructor
public class LivroController implements GenericController {

    private final LivroService service;
    private final LivroMapper mapper;

    private static final Logger logger = LoggerFactory.getLogger(LivroController.class);

    
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
    public ResponseEntity<Page<ResultadoPesquisaLivroDTO>> search(
            @RequestParam(value = "isbn", required = false) String isbn,
            @RequestParam(value = "titulo", required = false) String titulo,
            @RequestParam(value = "genero", required = false) GeneroLivro genero,
            @RequestParam(value = "ano-publicacao", required = false) Integer anoPublicacao,
            @RequestParam(value = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(value = "size", defaultValue = "10", required = false) Integer size) {
        logger.info(
                "Search request received with parameters: isbn={}, titulo={}, genero={}, anoPublicacao={}, page={}, size={}",
                isbn, titulo, genero, anoPublicacao, page, size);

        // var result = service.search(isbn, titulo, genero, anoPublicacao, page, size)
        // .stream().map(mapper::toDTO).collect(Collectors.toList());
        var result = service.search(isbn, titulo, genero, anoPublicacao, page, size)
                .map(mapper::toDTO);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> update(@PathVariable("id") String id, @RequestBody @Valid CadastroLivroDTO dto) {
        Livro livro = mapper.toEntity(dto);
        livro.setId(UUID.fromString(id));
        livro = service.save(livro);
        return ResponseEntity.ok(livro);
    }

}
