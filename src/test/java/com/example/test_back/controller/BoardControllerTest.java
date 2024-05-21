package com.example.test_back.controller;

import com.example.test_back.domain.dto.request.BoardRequest;
import com.example.test_back.domain.entity.Board;
import com.example.test_back.domain.repository.BoardRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
@Transactional
class BoardControllerTest {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void 보드_모두_가져오기_성공() throws Exception {
        mvc.perform(get("/api/boards"))
                .andExpect(status().isOk());
    }

    @Test
    void 보드_하나_가져오기_성공() throws Exception {
        Board board = new Board(1L,"name","text");
        boardRepository.save(board);
        mvc.perform(get("/api/boards/1"))
                .andExpect(status().isOk());
    }
    @Test
    void 보드_하나_가져오기_실패_이상한_ID() throws Exception {
        mvc.perform(get("/api/boards/asd"))
                .andExpect(status().isBadRequest());
    }
    @Test
    void 보드_하나_가져오기_실패_없는_ID() throws Exception {
        mvc.perform(get("/api/boards/1"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void 보드_추가하기_성공() throws Exception {
        String request = objectMapper.writeValueAsString(new BoardRequest("qwer", "asdf"));
        mvc.perform(post("/api/boards")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void 보드_삭제하기_실패() throws Exception {
        mvc.perform(delete("/api/boards/1"))
                .andExpect(result -> assertTrue(result.getResolvedException().getClass().isAssignableFrom(IllegalArgumentException.class)));

    }
}