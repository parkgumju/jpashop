package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.assertj.core.api.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    public void 회원가입() throws Exception {
        //given 이렇게~주어진
        Member member = new Member();
        member.setName("kim");
        //when 이렇게 했을때~
        Long savedId = memberService.join(member);
        //then 이렇게 된다~
        em.flush();
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test(expected =  IllegalStateException.class)
    public void  중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");
        Member member2 = new Member();
        member2.setName("kim");
        //when
        memberService.join(member1);
        memberService.join(member2); //예외가 밠갱 해야 한다.
       //expected =  IllegalStateException.class 사용 하게 되면 트라이 캐치문은 안써도됨
//        try{
//            memberService.join(member2); //예외가 밠갱 해야 한다.
//        } catch (IllegalStateException e) {
//            return;
//        }


        //then
//        fail('예외가 발생해야 한다.');
    }

}