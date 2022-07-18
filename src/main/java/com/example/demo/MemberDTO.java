package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class MemberDTO {

    @Builder
    @AllArgsConstructor
    @Getter
    public static class MemberRequestDTO {
        @NotNull(message = "이름이 null일순 없습니다.")
        private String name;
        private String job;
    }

    @Builder
    @AllArgsConstructor
    @Getter
    public static class MemberResponseDTO {
        private Long id;
        private String name;
        private String job;
    }


}
