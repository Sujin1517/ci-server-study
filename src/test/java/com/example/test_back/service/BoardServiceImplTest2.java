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

@ExtendWith(MockitoExtension.class)
class BoardServiceImplTest2 {
    @Mock
    private BoardRepository boardRepository;
    @InjectMocks
    private BoardServiceImpl boardService;

    @Test
    void getById() {
        Board board = new Board(1L, "test", "test");
        BDDMockito.given(boardRepository.findById(1L))
                .willReturn(Optional.of(board));


        Board byId = boardService.getBoard(1L);

//        행위 검증
        Mockito.verify(boardRepository, Mockito.times(1)).findById(1l);
//        상태 검증
        assertEquals("test", byId.getName());
        assertEquals("test", byId.getText());
        assertNotNull(byId.getId());
    }

    @Test
    void getByIdNotExist() {
        BDDMockito.given(boardRepository.findById(1L)).willReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, ()->{
            boardService.getBoard(1L);
        });
        Mockito.verify(boardRepository,Mockito.times(1)).findById(1L);

    }

    @Test
    void getAll() {
        BDDMockito.given(boardRepository.findAll()).willReturn(
                List.of(new Board(1L,"test", "test"),new Board(2L,"test", "test")));

        List<Board> all = boardService.getAllBoards();

        assertEquals(2, all.size());
        assertEquals("test", all.get(1).getName());
        Mockito.verify(boardRepository).findAll();
    }

    @Test
    void saveBoard() {
        BoardRequest request = new BoardRequest("test", "test");
        Board entity = Board.builder().name(request.name()).text(request.text()).build();
        BDDMockito.given(boardRepository.save(entity))
                .willReturn(entity);

        boardService.addBoard(request);

        Mockito.verify(boardRepository, Mockito.times(1)).save(entity);
    }
}