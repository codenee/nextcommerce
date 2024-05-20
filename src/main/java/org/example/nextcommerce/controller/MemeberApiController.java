package org.example.nextcommerce.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.nextcommerce.dto.MemberDto;
import org.example.nextcommerce.service.SessionLoginService;
import org.example.nextcommerce.service.MemberService;
import org.example.nextcommerce.common.annotation.LoginRequired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemeberApiController {

    private final MemberService memberService;
    private final SessionLoginService loginService;

    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody MemberDto dto){
        memberService.isValidMemberDto(dto);

        if(memberService.isDuplicatedEmail(dto.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        memberService.create(dto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/login")
    public ResponseEntity<HttpStatus> login(@RequestBody MemberDto dto){
        MemberDto dbDto = memberService.checkValidMember(dto);
        loginService.saveLoginMember(dbDto);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/session")
    @LoginRequired
    public Long sessionTest(){
        Long id = loginService.getSessionMemberId();
        log.info(Long.toString(id));
        return id;
    }



}
