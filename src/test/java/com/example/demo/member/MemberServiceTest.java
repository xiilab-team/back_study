package com.example.demo.member;

import com.example.demo.member.dto.MemberDTO;
import com.example.demo.member.entity.MemberEntity;
import com.example.demo.member.enumeration.Job;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.member.service.MemberService;
import com.example.demo.team.TeamEntity;
import com.example.demo.team.TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class MemberServiceTest {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private TeamRepository teamRepository;

    @Test
    void saveMember() {
        //given
        TeamEntity platformDEV = teamRepository.save(TeamEntity.builder().name("platformDEV").build());
        MemberDTO.MemberRequestDTO 김여훈 = MemberDTO.MemberRequestDTO.builder().name("김여훈").job(Job.POLICE).teamId(platformDEV.getId()).build();
        //when
        MemberDTO.MemberResponseDTO memberResponseDTO = memberService.saveMember(김여훈);
        Optional<MemberEntity> optionalMember = memberRepository.findById(memberResponseDTO.getId());
        //then
        assertThat(optionalMember).isPresent();
        assertThat(optionalMember.get().getTeam().getName()).isEqualTo(platformDEV.getName());
    }

    @Test
    void updateMember() {
        //given
        TeamEntity platformDEV = teamRepository.save(TeamEntity.builder().name("platformDEV").build());
        MemberDTO.MemberRequestDTO 김여훈 = MemberDTO.MemberRequestDTO.builder().name("김여훈").job(Job.POLICE).teamId(platformDEV.getId()).build();
        MemberDTO.MemberResponseDTO memberResponseDTO = memberService.saveMember(김여훈);
        //when
        memberService.updateMemberJob(memberResponseDTO.getId(),Job.DEVELOPER);
        MemberEntity memberEntity = memberRepository.findById(memberResponseDTO.getId()).get();
        //then
        assertThat(memberEntity.getJob()).isEqualTo(Job.DEVELOPER);
    }

}