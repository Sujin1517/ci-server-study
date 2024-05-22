package com.example.test_back.controller;

import com.example.test_back.domain.entity.Board;
import com.example.test_back.service.BoardServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@WebMvcTest(BoardController.class)
class BoardControllerTest {
    @MockBean
    private BoardServiceImpl boardService;
    @Autowired
    private MockMvc mvc;

    @Test
    void 보드_모두_가져오기_성공() throws Exception {
        BDDMockito.given(boardService.getAllBoards())
                .willReturn(List.of(
                        new Board(1L, "n1", "t1"),
                        new Board(2L, "n2", "t2"),
                        new Board(3L, "n3", "t3")
                ));


        mvc.perform(get("/api/boards"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$.size()").value(3))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void 보드_하나_가져오기_성공() throws Exception {
        BDDMockito.given(boardService.getBoard(1L))
                .willReturn(new Board(1L, "n1", "t1"));

        mvc.perform(get("/api/boards/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    void 보드_하나_가져오기_실패_이상한_ID() throws Exception {
        BDDMockito.given(boardService.getBoard(1L))
                .willReturn(new Board(1L, "n1", "t1"));

        mvc.perform(get("/api/boards/asd"))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    void 보드_하나_가져오기_실패_없는_ID() throws Exception {
//        BDDMockito.given(boardService.getBoard(999L))
//                .willThrow(IllegalArgumentException.class)
//                .willReturn(null);

//        BDDMockito.willThrow(new IllegalArgumentException())
//                        .given(boardService)
//                                .deleteBoard(999L);
//
//        mvc.perform(get("/api/boards/999"))
//                .andDo(MockMvcResultHandlers.print());
    }

//    @Test
//    void 보드_추가하기_성공() throws Exception {
//        String request = objectMapper.writeValueAsString(new BoardRequest("qwer", "asdf"));
//        mvc.perform(post("/api/boards")
//                .content(request)
//                .contentType(MediaType.APPLICATION_JSON)
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated());
//    }
//
//    @Test
//    void 보드_삭제하기_실패() throws Exception {
//        BDDMockito.given(boardService.getBoard(1L))
//                        .willReturn(null);
//
//        mvc.perform(delete("/api/boards/1"))
////                .andExpect(result -> assertTrue(result.getResolvedException().getClass().isAssignableFrom(IllegalArgumentException.class)));
////                .andExpect(status().is5xxServerError());
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print());
//
//    }
}