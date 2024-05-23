package com.example.test_back.service;

import com.example.test_back.domain.dto.request.BoardRequest;
import com.example.test_back.domain.entity.Board;
import com.example.test_back.domain.repository.BoardRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class BoardServiceImplTest2 {
    @Mock
    private BoardRepository boardRepository;
    @InjectMocks
    private BoardServiceImpl boardService;

    @Test
    void 아이디로_하나_가져오기_성공() {
        Board board = new Board(1L, "test", "test");
        BDDMockito.given(boardRepository.findById(1L))
                .willReturn(Optional.of(board));


        Board byId = boardService.getBoard(1L);

//        행위 검증
        Mockito.verify(boardRepository, Mockito.times(1)).findById(1L);
//        상태 검증
        assertEquals("test", byId.getName());
        assertEquals("test", byId.getText());
        assertNotNull(byId.getId());
    }

    @Test
    void 아이디로_하나_가져오기_해당_아이디_없음() {
        BDDMockito.given(boardRepository.findById(1L)).willReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, ()->{
            boardService.getBoard(1L);
        });
        Mockito.verify(boardRepository,Mockito.times(1)).findById(1L);

    }

    @Test
    void 보드_모두_가져오기_성공() {
        BDDMockito.given(boardRepository.findAll()).willReturn(
                List.of(new Board(1L,"test", "test"),new Board(2L,"test", "test")));

        List<Board> all = boardService.getAllBoards();

        assertEquals(2, all.size());
        assertEquals("test", all.get(1).getName());
        Mockito.verify(boardRepository).findAll();
    }

    @Test
    void 보드_추가_성공() {
        BoardRequest request = new BoardRequest("test", "test");
        BDDMockito.given(boardRepository.save(any())).willReturn(null);

        boardService.addBoard(request);

        Mockito.verify(boardRepository, Mockito.times(1)).save(any());
    }

    @Test
    void 보드_삭제_성공() {
        Long id = 1L;
        BDDMockito.given(boardRepository.findById(id))
                .willReturn(Optional.of(new Board(id, null, null)));

        boardService.deleteBoard(id);
    }
    @Test
    void 보드_삭제_실패_없는_아이디() {
        Long id = 1L;
        BDDMockito.given(boardRepository.findById(id))
                .willReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> boardService.deleteBoard(id));
//        Mockito.verify(boardRepository, Mockito.times(0)).findById(id);
    }
}