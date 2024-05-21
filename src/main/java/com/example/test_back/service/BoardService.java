package com.example.test_back.service;

import com.example.test_back.domain.dto.request.BoardRequest;
import com.example.test_back.domain.entity.Board;

import java.util.List;

public interface BoardService {
    List<Board> getAllBoards();
    Board getBoard(Long id);
    void addBoard(BoardRequest req);
    void deleteBoard(Long id);
}
