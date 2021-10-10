package jpaboard.boardV1.repository;

import jpaboard.boardV1.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void save(Member member){
        em.persist(member);
    }


    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public Member findLoginId(String loginId){
        return em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)
        .setParameter("loginId", loginId)
        .getSingleResult();
        //return em.find(Member.class, loginId);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();

    }
}
