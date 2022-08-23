package com.example.demo.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Slf4j
public class MemberRepository {

    public static final List<MemberDTO.MemberResponseDTO> MEMBERLIST = new ArrayList<>();

    public List<MemberDTO.MemberResponseDTO> getMemberList() {
        return MEMBERLIST;
    }

    public MemberDTO.MemberResponseDTO saveMember(MemberDTO.MemberRequestDTO memberRequestDTO) {
        long index = MEMBERLIST.size() + 1L;

        boolean result = MEMBERLIST.add(
                MemberDTO.MemberResponseDTO.builder()
                        .id(index)
                        .name(memberRequestDTO.getName())
                        .job(memberRequestDTO.getJob())
                        .build()
        );

        if (result) {
            log.info("{}가 추가되었습니다.",memberRequestDTO.getName());
            return MEMBERLIST.get((int) index - 1);
        } else {
            log.error("{}를 추가중에 에러가 발생하였습니다.",memberRequestDTO.getName());
            throw new IllegalArgumentException("값이 올바르지 않습니다.");
        }
    }

    public MemberDTO.MemberResponseDTO getMemberById(Long id) {
        return MEMBERLIST.stream().filter(member -> member.getId().equals(id)).findFirst().orElseThrow(() -> {
            throw new IllegalArgumentException("해당 id가 존재하지 않습니다.");
        });
    }
}
