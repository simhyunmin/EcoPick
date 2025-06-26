package EcoPick.controller;

import EcoPick.domain.member.Member;
import EcoPick.service.MemberService;
import EcoPick.service.StoreService;
import EcoPick.service.coupon.ConnectCompanyQueryService;
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

    @GetMapping("/{memberId}")
    public ResponseEntity<Member> getMemberInfo(@PathVariable Long memberId) {
        try {
            Member member = memberService.getMemberById(memberId);
            return ResponseEntity.ok(member);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{memberId}")
    public ResponseEntity<String> deleteMemberAccount(@PathVariable Long memberId) {
        try {
            memberService.deleteMemberById(memberId);
            return ResponseEntity.ok("Member deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{memberId}/count")
    public ResponseEntity<Integer> getMemberCount(@PathVariable Long memberId) {
        try {
            Integer count = memberService.getUserCount(memberId);
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/{memberId}/points")
    public ResponseEntity<Integer> getMemberPoints(@PathVariable Long memberId) {
        try {
            Integer points = memberService.getUserPoint(memberId);
            return ResponseEntity.ok(points);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
