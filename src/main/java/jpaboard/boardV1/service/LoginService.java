package jpaboard.boardV1.service;

import jpaboard.boardV1.domain.Member;
import jpaboard.boardV1.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {
    private final MemberRepository memberRepository;

    public Member login(String loginId, String password) {
        Member member = memberRepository.findLoginId(loginId);

        if (member.getPassword().equals(password)) {
//            log.info("name={}", member.getName());
            return member;
        } else {
            return null;
        }
    }
}
