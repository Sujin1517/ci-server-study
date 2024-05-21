package com.example.test_back.service;

import com.example.test_back.TestInit;
import com.example.test_back.domain.dto.request.BoardRequest;
import com.example.test_back.domain.entity.Board;
import com.example.test_back.domain.repository.BoardRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BoardServiceImplTest extends TestInit {

    @Test
    void getAllBoards() {
        List<Board> boards = new ArrayList<>();
        boards.add(Board.builder()
                .name("test1")
                .text("asdf")
                .build());
        boards.add(Board.builder()
                .name("test2")
                .text("qwer")
                .build());
        boardRepository.saveAll(boards);


        List<Board> all = boardService.getAllBoards();


        assertEquals(2, all.size());
    }

    @Test
    void getBoard() {
        Board board = Board.builder()
                .name("test")
                .text("asdf")
                .build();
        boardRepository.save(board);


        Board get = boardService.getBoard(board.getId());


        assertEquals("test", get.getName());
    }

    @Test
    void addBoard() {
        BoardRequest request = new BoardRequest("name", "text");


        boardService.addBoard(request);


        List<Board> allBoards = boardService.getAllBoards();
        assertEquals(1, allBoards.size());
        assertEquals("text", allBoards.get(0).getText());
    }

    @Test
    void deleteBoard() {
        Board board = Board.builder()
                .name("test")
                .text("asdf")
                .build();
        boardRepository.save(board);


        boardService.deleteBoard(board.getId());


        List<Board> allBoards = boardService.getAllBoards();
        assertEquals(0, allBoards.size());
    }
}