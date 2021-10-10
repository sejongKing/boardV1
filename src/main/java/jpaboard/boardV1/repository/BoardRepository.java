package jpaboard.boardV1.repository;

import jpaboard.boardV1.domain.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Slf4j
public class BoardRepository {
    private final EntityManager em;

    public void save(Board board) {
        em.persist(board);
    }

    public Board findOne(Long id){
        return em.find(Board.class, id);
    }

    public List<Board> findAll(){
        return em.createQuery("select b from Board b", Board.class)
                .getResultList();
    }


    public void update(Long id, String title, String context){
        Board board = em.find(Board.class, id);
        board.setTitle(title);
        board.setContext(context);
    }

    public void remove(Long id) {
        Board board = em.find(Board.class, id);
        em.remove(board);
    }

}
