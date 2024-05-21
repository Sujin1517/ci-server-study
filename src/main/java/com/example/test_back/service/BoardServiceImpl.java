package com.example.test_back.service;

import com.example.test_back.domain.dto.request.BoardRequest;
import com.example.test_back.domain.entity.Board;
import com.example.test_back.domain.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{
    private final BoardRepository boardRepository;

    @Override
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    @Override
    public Board getBoard(Long id) {
        return boardRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public void addBoard(BoardRequest req) {
        boardRepository.save(Board.builder()
                .name(req.name())
                .text(req.text())
                .build());
    }

    @Override
    public void deleteBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        boardRepository.delete(board);
    }
}
