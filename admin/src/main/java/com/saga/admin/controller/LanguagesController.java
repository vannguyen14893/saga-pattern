package com.saga.admin.controller;

import com.saga.admin.dto.request.languages.CreateLanguagesRequest;
import com.saga.admin.dto.request.languages.UpdateLanguagesRequest;
import com.saga.admin.entity.Languages;
import com.saga.admin.service.LanguagesService;
import com.saga.dto.response.ResponseSuccess;
import com.saga.response.controller.BaseController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/languages")
@RequiredArgsConstructor
public class LanguagesController extends BaseController {
    private final LanguagesService languagesService;

    @GetMapping
    public ResponseEntity<ResponseSuccess<List<Languages>>> findAll() {
        return execute(languagesService.findAll(), 200);
    }

    @GetMapping("/active")
    public ResponseEntity<ResponseSuccess<List<Languages>>> findAllByActiveIsTrue() {
        return execute(languagesService.findAllByActiveIsTrue(), 200);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseSuccess<Languages>> findById(@PathVariable Long id) {
        return execute(languagesService.findById(id), 200);
    }

    @PostMapping
    public ResponseEntity<ResponseSuccess<Languages>> create(@RequestBody @Valid CreateLanguagesRequest createLanguagesRequest) {
        return execute(languagesService.create(createLanguagesRequest), 201);
    }

    @PutMapping
    public ResponseEntity<ResponseSuccess<Languages>> update(@RequestBody @Valid UpdateLanguagesRequest updateLanguagesRequest) {
        return execute(languagesService.update(updateLanguagesRequest), 200);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseSuccess<Long>> deleteById(@PathVariable Long id) {
        return execute(languagesService.delete(id), 200);
    }
}
