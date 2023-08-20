package com.example.query.pratice.repository;

import static com.example.query.pratice.entity.QBoard.board;

import com.example.query.pratice.entity.Board;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class BoardQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Board> findByTitle(String title) {
        return queryFactory.selectFrom(board)
                           .where(board.title.eq(title))
                           .fetch();
    }
}
