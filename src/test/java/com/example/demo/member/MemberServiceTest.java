package com.example.demo.member;

import com.example.demo.team.TeamEntity;
import com.example.demo.team.TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepositoryJPA memberRepositoryJPA;
    @Autowired
    private TeamRepository teamRepository;

    @Test
    void saveMember() {
        //given
        TeamEntity platformDEV = teamRepository.save(TeamEntity.builder().name("platformDEV").build());
        MemberDTO.MemberRequestDTO 김여훈 = MemberDTO.MemberRequestDTO.builder().name("김여훈").job(Job.POLICE).teamId(platformDEV.getId()).build();
        //when
        MemberDTO.MemberResponseDTO memberResponseDTO = memberService.saveMember(김여훈);
        Optional<MemberEntity> optionalMember = memberRepositoryJPA.findById(memberResponseDTO.getId());
        //then
        assertThat(optionalMember).isPresent();
        assertThat(optionalMember.get().getTeam().getName()).isEqualTo(platformDEV.getName());
    }

    @Test
    void getMemberList() {
    }

    @Test
    void getMemberById() {
    }

}