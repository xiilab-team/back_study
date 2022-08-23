package com.example.demo.member;

import com.example.demo.team.TeamEntity;
import com.example.demo.team.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberRepositoryJPA memberRepositoryJPA;
    private final TeamRepository teamRepository;

    public MemberDTO.MemberResponseDTO saveMember(MemberDTO.MemberRequestDTO memberRequestDTO) {
        //요청한 팀이 존재하는지 확인하고 없다면 exception 발생
        TeamEntity team = teamRepository.findById(memberRequestDTO.getTeamId()).orElseThrow(() -> {
            throw new IllegalArgumentException("해당 team이 존재하지 않습니다.");
        });
        //memberEntity 추가
        MemberEntity member = memberRepositoryJPA.save(
                MemberEntity.memberBuilder().requestDTO(memberRequestDTO).team(team).build());
        //convert to DTO
        return MemberDTO.MemberResponseDTO.convertDTO().member(member).build();
    }

    public List<MemberDTO.MemberResponseDTO> getMemberList() {
        return memberRepository.getMemberList();
    }

    public MemberDTO.MemberResponseDTO getMemberById(Long id) {
        return memberRepository.getMemberById(id);
    }
}
