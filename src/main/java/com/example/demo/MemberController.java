package com.example.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {
    private final MemberService memberService;

    @PostMapping()
    public ResponseEntity<MemberDTO.MemberResponseDTO> saveMember(
            @RequestBody @Valid MemberDTO.MemberRequestDTO memberRequestDTO) {

        return new ResponseEntity<>(memberService.saveMember(memberRequestDTO), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<MemberDTO.MemberResponseDTO>> getMemberList() {
        return new ResponseEntity<>(memberService.getMemberList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO.MemberResponseDTO> getMemberById(@PathVariable Long id) {
        return new ResponseEntity<>(memberService.getMemberById(id), HttpStatus.OK);
    }
}
