package com.example.demo.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepositoryJPA extends JpaRepository<MemberEntity, Long> {
    MemberEntity findByName(String name);
}
