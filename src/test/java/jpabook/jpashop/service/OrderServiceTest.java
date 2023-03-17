package jpabook.jpashop.service;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class OrderServiceTest {
    
    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() throws Exception {
        Member member = getMember();

        Book book = getBook();

        int orderCount = 2;
        Long orderId =  orderService.oder(member.getId(),book.getId(), orderCount);

        Order getOrder = orderRepository.findOne(orderId);
        
        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getSTatus());
        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getSTatus());
        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getSTatus());
        assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getSTatus());
        
        
        
    }

    private Book getBook() {
        Book book = new Book();
        book.setName("시골 JPA");
        book.setStockQuantity(10);
        em.persist(book);
        return book;
    }

    private Member getMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울","강가","123-1133"));
        em.persist(member);
        return member;
    }

    @Test(expected = NotEnoughStockException.class)
    public void 상품취소() throws Exception {
        createMember();

        Order Order = orderRepository.findOne(orderId);




    }


    @Test
    public void 상품주문() throws Exception {

    }
}