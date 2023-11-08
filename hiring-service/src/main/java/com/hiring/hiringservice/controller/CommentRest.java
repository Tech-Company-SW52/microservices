package com.hiring.hiringservice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hiring.hiringservice.entity.Comment;

import com.hiring.hiringservice.service.ICommentService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/hiring/comments")
public class CommentRest {

    @Autowired
    ICommentService commentService;

    @GetMapping
    public ResponseEntity<List<Comment>> listAllComments() {
        List<Comment> comments = commentService.findCommentAll();
        if (comments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(comments);
    }

    @GetMapping(value = "/contract/{contractId}")
    public ResponseEntity<List<Comment>> listCommentsByContract(
            @PathVariable("contractId") Long contractId) {
        List<Comment> comments = commentService.findByContractId(contractId);
        if (comments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(comments);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable("id") Long id) {
        log.info("Fetching Comment with id {}", id);
        Comment comment = commentService.getComment(id);
        if (comment == null) {
            log.error("Comment with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(comment);
    }

    @PostMapping(value = "/add/contract/{contractId}/client/{clientId}")
    public ResponseEntity<Comment> createComment(
            @PathVariable("contractId") Long contractId,
            @PathVariable("clientId") Long clientId,
            @Valid @RequestBody Comment comment,
            BindingResult result) {

        log.info("Creating Comment : {}", comment);
        if (result.hasErrors()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Comment commentDB = commentService.createComment(comment, contractId, clientId);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentDB);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Comment> updateComment(
            @PathVariable("id") Long id,
            @RequestBody Comment comment) {

        log.info("Updating Comment with id {}", id);
        Comment currentComment = commentService.getComment(id);
        if (currentComment == null) {
            log.error("Unable to update. Comment with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        comment.setId(id);
        currentComment = commentService.updateComment(comment);
        return ResponseEntity.ok(currentComment);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Comment> deleteComment(@PathVariable("id") Long id) {
        log.info("Fetching & Deleting Comment with id {}", id);
        Comment comment = commentService.getComment(id);
        if (comment == null) {
            log.error("Unable to delete. Comment with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        comment = commentService.deleteComment(comment);
        return ResponseEntity.ok(comment);
    }

    private String formatMessage(BindingResult result) {
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String, String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
