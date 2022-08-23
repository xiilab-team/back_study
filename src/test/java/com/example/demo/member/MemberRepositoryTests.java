package com.example.demo.member;

import com.example.demo.member.entity.MemberEntity;
import com.example.demo.member.enumeration.Job;
import com.example.demo.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("member 추가 테스트")
    void add_member_test() {
        MemberEntity member = memberRepository.save(MemberEntity.builder()
                .name("김연훈")
                .job(Job.DEVELOPER)
                .build());

        MemberEntity memberEntity = memberRepository.findById(member.getId()).get();

        assertThat(member.getId()).isEqualTo(memberEntity.getId());
        assertThat(member.getName()).isEqualTo(memberEntity.getName());
        assertThat(member.getJob()).isEqualTo(memberEntity.getJob());
    }

    @Test
    @DisplayName("member update 테스트")
    void update_member_test() {
        MemberEntity member = memberRepository.save(MemberEntity.builder()
                .name("김연훈")
                .job(Job.DEVELOPER)
                .build());

        member.updateJob(Job.POLICE);

        MemberEntity memberEntity = memberRepository.findById(member.getId()).get();

        assertThat(memberEntity.getJob()).isEqualTo(Job.POLICE);
    }

    @Test
    @DisplayName("member read 테스트")
    void read_member_test() {
        MemberEntity member = memberRepository.save(MemberEntity.builder()
                .name("김연훈1")
                .job(Job.DEVELOPER)
                .build());

        MemberEntity memberEntity = memberRepository.findByName(member.getName());

        assertThat(member.getId()).isEqualTo(memberEntity.getId());
        assertThat(member.getName()).isEqualTo(memberEntity.getName());
        assertThat(member.getJob()).isEqualTo(memberEntity.getJob());
    }

    @Test
    @DisplayName("member delete 테스트")
    void delete_member_test() {
        MemberEntity member = memberRepository.save(MemberEntity.builder()
                .name("김연훈1")
                .job(Job.DEVELOPER)
                .build());

        memberRepository.delete(member);

        Optional<MemberEntity> byId = memberRepository.findById(member.getId());

        assertTrue(byId.isEmpty());
    }
}
