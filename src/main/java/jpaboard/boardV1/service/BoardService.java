package jpaboard.boardV1.service;

import jpaboard.boardV1.domain.Board;
import jpaboard.boardV1.domain.Member;
import jpaboard.boardV1.repository.BoardRepository;
import jpaboard.boardV1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    public List<Board> findBoards(){
        return boardRepository.findAll();
    }

    public void save(Board board) {
        boardRepository.save(board);
    }

    public Board findBoard(Long id){
        return boardRepository.findOne(id);
    }

    public void update(Long id, String title, String context){
        boardRepository.update(id, title, context);
    }

    public void remove(Long id) {
        boardRepository.remove(id);
    }
}
