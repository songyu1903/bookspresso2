package com.example.bookspresso.controller.admin;

import com.example.bookspresso.dto.admin.discussion.AttendMemberDTO;
import com.example.bookspresso.dto.admin.discussion.DebateSearchDTO;
import com.example.bookspresso.dto.admin.discussion.FinishedDebateDTO;
import com.example.bookspresso.dto.admin.discussion.ManageDebateDTO;
import com.example.bookspresso.dto.admin.page.AdminPageRequestDTO;
import com.example.bookspresso.dto.admin.page.AdminPageSetDTO;
import com.example.bookspresso.service.admin.ManageDebateService;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin/debate")
@RequiredArgsConstructor
public class DiscussionController {

    private final ManageDebateService manageDebateService;


//    private final Map<String, String> debateTypeId;

    // 현재 진행 중인 토론
    @GetMapping("/onGoing")
    public String allDiscussion(Model model,
                                AdminPageRequestDTO adminPageRequestDTO){

        List<ManageDebateDTO> list = manageDebateService.findDebateList(adminPageRequestDTO);

        list.forEach(manageDebateDTO ->{
            List<AttendMemberDTO> attendMemberList = manageDebateService.findAttendMemberList(manageDebateDTO.getDebateId());
            manageDebateDTO.setAttendList(attendMemberList);
        });


        int total = manageDebateService.findDebateCount();
        AdminPageSetDTO adminPageSetDTO = new AdminPageSetDTO(adminPageRequestDTO, total);


        model.addAttribute("total", total);
        model.addAttribute("list", list);
        model.addAttribute("adminPageSetDTO", adminPageSetDTO);

        return "admin/discussion/onGoingDc";
    }

    @GetMapping("/onGoing/search")
    public String OnDebateSearch(DebateSearchDTO debateSearchDTO,
                                 AdminPageRequestDTO adminPageRequestDTO,
                                 Model model){

        List<ManageDebateDTO> list = manageDebateService.findSearchDebateList(debateSearchDTO.getSearchType(),
                debateSearchDTO.getKeyword(), adminPageRequestDTO.getPage(),
                adminPageRequestDTO.getAmount());

        int total = manageDebateService.findSearchDebateTotal(debateSearchDTO.getSearchType(),
                debateSearchDTO.getKeyword(), adminPageRequestDTO.getPage(),
                adminPageRequestDTO.getAmount());

        AdminPageSetDTO adminPageSetDTO = new AdminPageSetDTO(adminPageRequestDTO, total);

        model.addAttribute("total", total);
        model.addAttribute("list", list);
        model.addAttribute("adminPageSetDTO", adminPageSetDTO);


        return "/admin/discussion/onGoingDc";
    }

    @GetMapping("/finished")
    public String endDiscussion(Model model,
                                AdminPageRequestDTO adminPageRequestDTO){

        List<ManageDebateDTO> list = manageDebateService.findEndDebateList(adminPageRequestDTO);

        list.forEach(manageDebateDTO ->{
            List<AttendMemberDTO> attendMemberList = manageDebateService.findAttendMemberList(manageDebateDTO.getDebateId());
            manageDebateDTO.setAttendList(attendMemberList);
        });

        int total = manageDebateService.findEndDebateCount();
        AdminPageSetDTO adminPageSetDTO = new AdminPageSetDTO(adminPageRequestDTO, total);

        model.addAttribute("total", total);
        model.addAttribute("list", list);
        model.addAttribute("adminPageSetDTO", adminPageSetDTO);

        return "admin/discussion/finishedDc";
    }

    @GetMapping("/finished/search")
    public String finishedSearch(DebateSearchDTO debateSearchDTO,
                                 AdminPageRequestDTO adminPageRequestDTO,
                                 Model model){
        List<FinishedDebateDTO> list = manageDebateService.findSearchEndDebateList(debateSearchDTO.getSearchType(),
                debateSearchDTO.getKeyword(), adminPageRequestDTO.getPage(),
                adminPageRequestDTO.getAmount());

        int total = manageDebateService.findSearchEndDebateTotal(debateSearchDTO.getSearchType(),
                debateSearchDTO.getKeyword(), adminPageRequestDTO.getPage(),
                adminPageRequestDTO.getAmount());

        AdminPageSetDTO adminPageSetDTO = new AdminPageSetDTO(adminPageRequestDTO, total);
        model.addAttribute("total", total);
        model.addAttribute("list", list);
        model.addAttribute("adminPageSetDTO", adminPageSetDTO);

        return "admin/discussion/finishedDc";

    }


    @GetMapping("/recruiting")
    public String recruiting(AdminPageRequestDTO adminPageRequestDTO,
                             DebateSearchDTO debateSearchDTO,
                             Model model){

        List<ManageDebateDTO> list = manageDebateService.findRecruitingDebate(adminPageRequestDTO);

        int total = manageDebateService.findRecrutingDebateCount(debateSearchDTO);

        AdminPageSetDTO adminPageSetDTO = new AdminPageSetDTO(adminPageRequestDTO, total);

        model.addAttribute("total", total);
        model.addAttribute("list", list);
        model.addAttribute("adminPageSetDTO", adminPageSetDTO);


        return "admin/discussion/recruitingDc";
    }




}
