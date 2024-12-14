package com.example.mylibrary.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.mylibrary.controller.dto.AutorDTO;
import com.example.mylibrary.controller.dto.ErrorResponse;
import com.example.mylibrary.exceptions.DuplicatedRegistryException;
import com.example.mylibrary.model.Autor;
import com.example.mylibrary.service.AutorService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/autores")
public class AutorController {

    private final AutorService service;

    public AutorController(AutorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody AutorDTO autor) {
        try {
            var entity = autor.mapearParaAutor();
            var result = service.salvar(entity);
            var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(result.getId())
                    .toUri();
            return ResponseEntity.created(uri).body(result);
        } catch (DuplicatedRegistryException e) {
            var error = ErrorResponse.conflict(e.getMessage());
            return ResponseEntity.status(error.status()).body(error);
        }

    }

    @GetMapping("{id}")
    public ResponseEntity<Autor> getById(@PathVariable("id") String id) {
        var autor = service.getById(id);
        return autor != null ? ResponseEntity.ok(autor) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // @GetMapping
    // public ResponseEntity<List<AutorDTO>> search(@RequestParam(value = "nome",
    // required = false) String nome,
    // @RequestParam(value = "nacionalidade", required = false) String
    // nacionalidade) {
    // var result = service.search(nome, nacionalidade).stream().map(autor -> new
    // AutorDTO(autor.getId(),
    // autor.getNome(),
    // autor.getDataNascimento(),
    // autor.getNacionalidade())).collect(Collectors.toList());

    // return ResponseEntity.ok(result);

    // }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable("id") String id, @RequestBody AutorDTO autor) {
        try {
            Autor result = service.getById(id);
            if (result == null) {
                return ResponseEntity.notFound().build();
            }
            result.setNome(autor.nome());
            result.setDataNascimento(autor.dataNascimento());
            result.setNacionalidade(autor.nacionalidade());
            return ResponseEntity.ok(service.update(result));
        } catch (DuplicatedRegistryException e) {
            var error = ErrorResponse.conflict(e.getMessage());
            return ResponseEntity.status(error.status()).body(error);
        }
    }

}
