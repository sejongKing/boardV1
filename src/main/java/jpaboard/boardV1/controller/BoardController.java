package jpaboard.boardV1.controller;

import jpaboard.boardV1.domain.Board;
import jpaboard.boardV1.domain.Member;
import jpaboard.boardV1.repository.MemberRepository;
import jpaboard.boardV1.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.rule.Mode;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;
    private final MemberRepository memberRepository;

    @GetMapping("/board/add")
    public String createForm(Model model) {
        model.addAttribute("boardForm", new BoardForm());
        return "/board/createBoardForm";
    }

    @PostMapping("/board/add")
    @Transactional
    public String create(BoardForm form,
                         @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)Member loginMember){
        Board board = new Board();

        board.setContext(form.getContent());
        board.setTitle(form.getTitle());
        board.setPostDate(LocalDateTime.now());
        //log.info("name={}", loginMember.getName());
        Member member = memberRepository.findOne(loginMember.getId());
        board.setMember(member);
        board.setName(loginMember.getName());
        boardService.save(board);

        return "redirect:/";
    }

    @GetMapping("/board/edit/{boardId}")
    public String editForm(@PathVariable Long boardId, Model model){
        Board board = boardService.findBoard(boardId);
        model.addAttribute("board", board);

        return "board/editBoard";
    }

    @PostMapping("/board/edit/{boardId}")
    @Transactional
    public String edit(@PathVariable Long boardId,
                       @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)Member loginMember,
                       Board editBoard) {
        Board board = boardService.findBoard(boardId);
        if (board.getMember().getId() != loginMember.getId()) {
            return "redirect:/";
        }

        boardService.update(boardId, editBoard.getTitle(), editBoard.getContext());

        return "redirect:/";
    }

    @PostMapping("/board/remove/{boardId}")
    @Transactional
    public String remove(@PathVariable Long boardId,
                         @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember) {
        Board board = boardService.findBoard(boardId);
        if (board.getMember().getId() != loginMember.getId()) {
            return "redirect:/";
        }
        boardService.remove(boardId);
        return "redirect:/";
    }


    @GetMapping("/boards")
    public String list(Model model) {
        List<Board> boards = boardService.findBoards();
        model.addAttribute("boards", boards);
        return "board/boardList";
    }

    @GetMapping("/board/{boardId}")
    public String board(@PathVariable Long boardId, Model model) {
        Board board = boardService.findBoard(boardId);
        model.addAttribute("board", board);

        return "board/board";
    }
}
