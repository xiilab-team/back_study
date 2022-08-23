package com.example.demo.member.service;

import com.example.demo.member.enumeration.Job;
import com.example.demo.member.dto.MemberDTO;
import com.example.demo.member.entity.MemberEntity;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.team.TeamEntity;
import com.example.demo.team.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final TeamRepository teamRepository;

    public MemberDTO.MemberResponseDTO saveMember(MemberDTO.MemberRequestDTO memberRequestDTO) {
        //요청한 팀이 존재하는지 확인하고 없다면 exception 발생
        TeamEntity team = teamRepository.findById(memberRequestDTO.getTeamId()).orElseThrow(() -> {
            throw new IllegalArgumentException("해당 team이 존재하지 않습니다.");
        });
        //memberEntity 추가
        MemberEntity member = memberRepository.save(
                MemberEntity.memberBuilder().requestDTO(memberRequestDTO).team(team).build());
        //convert to DTO
        return MemberDTO.MemberResponseDTO.convertDTO().member(member).build();
    }

    public List<MemberDTO.MemberResponseDTO> getMemberList() {
        List<MemberEntity> memberEntities = memberRepository.findAll();

        return memberEntities.stream().map(memberEntity ->
                MemberDTO.MemberResponseDTO.convertDTO().member(memberEntity).build()).collect(Collectors.toList());
    }

    public MemberDTO.MemberResponseDTO getMemberById(Long id) {
        Optional<MemberEntity> optionalMember = memberRepository.findById(id);

        MemberEntity memberEntity = optionalMember.orElseThrow(IllegalArgumentException::new);

        return MemberDTO.MemberResponseDTO.convertDTO().member(memberEntity).build();
    }

    public MemberDTO.MemberResponseDTO updateMemberJob(Long id, Job job) {
        Optional<MemberEntity> optionalMember = memberRepository.findById(id);

        MemberEntity memberEntity = optionalMember.orElseThrow(IllegalArgumentException::new);

        memberEntity.updateJob(job);

        return MemberDTO.MemberResponseDTO.convertDTO().member(memberEntity).build();
    }

    public void deleteMember(Long id) throws IllegalArgumentException{
        memberRepository.deleteById(id);
    }
}
