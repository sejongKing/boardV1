package jpaboard.boardV1.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.criterion.Order;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String loginId;
    private String name;
    private String password;

    @OneToMany(mappedBy = "member")
    private List<Board> boards = new ArrayList<>();

}
