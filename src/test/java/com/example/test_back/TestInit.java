package com.example.test_back;

import com.example.test_back.controller.BoardController;
import com.example.test_back.domain.repository.BoardRepository;
import com.example.test_back.service.BoardService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Transactional
public class TestInit {
    @Autowired
    protected EntityManager em;

    @Autowired
    protected BoardRepository boardRepository;

    @Autowired
    private BoardController boardController;

    @Autowired
    protected BoardService boardService;

    @BeforeEach
    void dataInit() {
        boardRepository.deleteAll();
//        em.clear();
//        em.flush();
    }
}
