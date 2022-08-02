package com.example.demo.member;

import com.example.demo.team.TeamEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "TB_MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(value = EnumType.STRING)
    private Job job;
    @ManyToOne
    private TeamEntity team;

    @Builder(builderClassName = "memberBuilder", builderMethodName = "memberBuilder")
    public MemberEntity (MemberDTO.MemberRequestDTO requestDTO, TeamEntity team) {
        this.name = requestDTO.getName();
        this.job = requestDTO.getJob();
        this.team = team;
    }
}
