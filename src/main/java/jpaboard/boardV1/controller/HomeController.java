package jpaboard.boardV1.controller;

import jpaboard.boardV1.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@Slf4j
public class HomeController {
    @GetMapping("/")
    public String homeLogin(
            @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false)Member loginMember,
            Model model){
        if (loginMember == null) {
            //ㅇㄹㅇㄹ
            return "index";
        }
//        log.info("name={}", loginMember.getName());
        model.addAttribute("member", loginMember);
        return "loginHome";

    }
}
