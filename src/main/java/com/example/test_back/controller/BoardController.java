package com.example.test_back.controller;

import com.example.test_back.domain.dto.request.BoardRequest;
import com.example.test_back.domain.entity.Board;
import com.example.test_back.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping
    public List<Board> getAllBoard() {
        return boardService.getAllBoards();
    }

    @GetMapping("/{id}")
    public Board getBoard(@PathVariable("id") Long id) {
        return boardService.getBoard(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addBoard(@RequestBody BoardRequest req) {
        boardService.addBoard(req);
    }

    @DeleteMapping("/{id}")
    public void deleteBoard(@PathVariable("id") Long id) {
        boardService.deleteBoard(id);
    }
}
