package com.example.demo;

import com.example.demo.member.enumeration.Job;
import com.example.demo.member.dto.MemberDTO;
import com.example.demo.member.entity.MemberEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

@SpringBootTest
class EntityManagerTests {
    @Autowired
    private EntityManagerFactory entityManagerFactory;

    @Test
    @DisplayName("생성테스트")
    void create_test() {
        //요청1
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        //요청1 transaction 시작
        transaction.begin();
        MemberDTO.MemberRequestDTO 김연훈 = MemberDTO.MemberRequestDTO.builder().name("김연훈").job(Job.POLICE).build();
        //비영속
        MemberEntity member = MemberEntity.memberBuilder().requestDTO(김연훈).build();
        //영속
        entityManager.persist(member);
        //transaction commit
        transaction.commit();
        System.out.println(member);
    }
}
