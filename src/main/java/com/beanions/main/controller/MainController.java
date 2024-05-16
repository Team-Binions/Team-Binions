package com.beanions.main.controller;

import com.beanions.auth.model.AuthDetails;
import com.beanions.common.dto.PostDTO;
import com.beanions.main.service.MainService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Controller
@AllArgsConstructor
public class MainController{
    private final MainService mainService;

    @GetMapping(value = {"/","/main"})
    public String main(Authentication authentication, Model model){

        if( authentication != null ) {
            AuthDetails dto = (AuthDetails) authentication.getPrincipal();
            Collection<GrantedAuthority> authlist = dto.getAuthorities();
            Iterator<GrantedAuthority> authlist_it= authlist.iterator();

            //240516 login한 회원 닉네임 정보 가져오기
//            AuthDetails loginUser = (AuthDetails) authentication.getPrincipal();
//            model.addAttribute("loginUserInfo", loginUser.getLoginUserDTO().getNickname());

            while(authlist_it.hasNext()) {
                GrantedAuthority authority= authlist_it.next();
                //설정되어 있는 권한 중 ROLE_ADMIN이 있다면
                if(authority.getAuthority().equals("ADMIN")) {
                    return "redirect:/auth/logout";
                }
            }
        }

        //예수다
        List<PostDTO> freeBoardByBride = mainService.selectFreeBoardByBride();
        model.addAttribute("freeBoardByBridge", freeBoardByBride);

        //예수다
        List<PostDTO> freeBoardByGroom = mainService.selectFreeBoardByGroom();
        model.addAttribute("freeBoardByGroom", freeBoardByGroom);

        //예리뷰
        List<PostDTO> reviewBoard = mainService.selectReview();
        model.addAttribute("reviewBoard", reviewBoard);

        //예피셜 > 매거진
        List<PostDTO> magazineBoard = mainService.selectMagazine();
        model.addAttribute("magazineBoard", magazineBoard.get(0));

        // 공지사항
        List<PostDTO> noticeBoard = mainService.selectNotice();
        model.addAttribute("noticeBoard", noticeBoard);

        return "user/main";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

}