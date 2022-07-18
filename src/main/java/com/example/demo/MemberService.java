package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberDTO.MemberResponseDTO saveMember(MemberDTO.MemberRequestDTO memberRequestDTO) {
        return memberRepository.saveMember(memberRequestDTO);
    }

    public List<MemberDTO.MemberResponseDTO> getMemberList() {
        return memberRepository.getMemberList();
    }

    public MemberDTO.MemberResponseDTO getMemberById(Long id) {
        return memberRepository.getMemberById(id);
    }
}
