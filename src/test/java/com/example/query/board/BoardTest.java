package com.example.query.board;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.query.pratice.entity.Board;
import com.example.query.pratice.repository.BoardQueryRepository;
import com.example.query.pratice.repository.BoardRepository;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardTest {

    @Autowired
    private BoardQueryRepository boardQueryRepository;
    @Autowired
    private BoardRepository boardRepository;

    @AfterEach
    void tear_down() throws Exception {
        boardRepository.deleteAllInBatch();
    }

    @Test
    void querydsl_select_test() throws Exception {

        Board board = Board.builder()
                           .title("안녕하세요")
                           .content("반갑습니다")
                           .build();

        boardRepository.save(board);

        List<Board> boardList = boardQueryRepository.findByTitle("안녕하세요");

        System.out.println(boardList.get(0).toString());

        assertThat(boardList).satisfies(
            b -> assertThat(b.size()).isEqualTo(1),
            b -> assertThat(b.get(0).getContent()).isEqualTo("반갑습니다")
        );
    }

    @Test
    void querydsl_exists_test() throws Exception {

        Board board = Board.builder()
                           .title("안녕하세요")
                           .content("반갑습니다")
                           .build();

        boardRepository.save(board);

        boolean exists = boardQueryRepository.existsByTitle("안녕하세요");

        assertThat(exists).isEqualTo(true);
    }
}
