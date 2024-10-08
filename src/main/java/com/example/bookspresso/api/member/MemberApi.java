package com.example.bookspresso.api.member;

import com.example.bookspresso.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberApi {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/join/check-loginId/{loginId}")
    public int checkLoginId(@PathVariable("loginId") String loginId){
        int loginIdCount = memberService.findLoginId(loginId);
        System.out.println("loginIdCount =@@@@=" + loginIdCount);
        return loginIdCount;

    }

    @GetMapping(value = "/join/check-nickname")
    public int checkNickname(String nickname){
        return memberService.findNickname(nickname);
    }

    @PostMapping("/join/check-email/{emailValue}")
    public int checkEmail(@PathVariable("emailValue") String emailValue){
        return memberService.findEmail(emailValue);
        // 1이면 존재 , 0 이면 아무도 사용하고 있지 않음
    }


    //로그인
    @PostMapping("/login/findMemberId/{loginId}/{password}")
    public boolean findMemberId(@PathVariable("loginId") String loginId,
                             @PathVariable("password") String password){

        try {
            memberService.findMemberId(loginId, password);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}
