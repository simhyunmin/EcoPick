package EcoPick.controller;

import EcoPick.domain.member.Member;
import EcoPick.service.MemberService;
import EcoPick.service.StoreService;
import EcoPick.service.coupon.ConnectCompanyQueryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;
    private final StoreService storeService;
    private final ConnectCompanyQueryService connectCompanyQueryService;

    @GetMapping("/me")
    public ResponseEntity<Member> getMyInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("member") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Member member = (Member) session.getAttribute("member");
        return ResponseEntity.ok(member);
    }

    @DeleteMapping("/me")
    public ResponseEntity<String> deleteMyAccount(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("member") == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Member member = (Member) session.getAttribute("member");
        memberService.deleteMemberById(member.getId());
        return ResponseEntity.ok("Member deleted");
    }

    @GetMapping("/me/count")
    public Integer getMyCount(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("member") == null) {
            throw new RuntimeException("로그인이 필요합니다.");
        }
        Member member = (Member) session.getAttribute("member");
        return memberService.getUserCount(member.getId());
    }

    @GetMapping("/me/points")
    public Integer getMyPoints(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("member") == null) {
            throw new RuntimeException("로그인이 필요합니다.");
        }
        Member member = (Member) session.getAttribute("member");
        return memberService.getUserPoint(member.getId());
    }
}
