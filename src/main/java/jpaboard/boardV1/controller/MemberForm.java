package jpaboard.boardV1.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {
    private String loginId;
    private String name;
    private String password;

}
