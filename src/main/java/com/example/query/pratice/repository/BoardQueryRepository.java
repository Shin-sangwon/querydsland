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

    /*
    NOTE: SPRING DATA JPA의 exists... 와 querydsl의 exists.. 는 count 쿼리를 날리므로 일치하는 레코드가 많아질수록 성능에 영향이 커짐.
    따라서, 아래와 같은 방식으로 (limit 1 추가) 성능 문제 해결
     */
    public boolean existsByTitle(String title) {
        return queryFactory.from(board)
                           .where(board.title.eq(title))
                           .select(board.id)
                           .fetchFirst() != null;

    }
}
