package com.example.demo.member;

import lombok.*;

import javax.validation.constraints.NotNull;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MemberDTO {

    @Builder
    @AllArgsConstructor
    @Getter
    public static class MemberRequestDTO {
        @NotNull(message = "이름이 null일순 없습니다.")
        private String name;
        private Job job;
        private Long teamId;
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class MemberResponseDTO {
        private Long id;
        private String name;
        private Job job;
        private Long teamId;

        @Builder(builderMethodName = "convertDTO", builderClassName = "convertDTO")
        public MemberResponseDTO (MemberEntity member) {
            this.id = member.getId();
            this.name = member.getName();
            this.job = member.getJob();
            this.teamId = member.getTeam().getId();
        }
    }


}
