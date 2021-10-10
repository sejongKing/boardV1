package jpaboard.boardV1.domain;

import jpaboard.boardV1.controller.SessionConst;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Slf4j
public class Board {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
    private String name;

    private String title;
    private String context;
    private LocalDateTime postDate;

    public void setMember(Member member) {
        this.member = member;
        //log.info("name={}",member.getName());
        member.getBoards().add(this);
    }


}
