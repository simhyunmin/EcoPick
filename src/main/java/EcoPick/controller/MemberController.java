package EcoPick.controller;

import EcoPick.domain.member.Member;
import EcoPick.domain.store.Store;
import EcoPick.service.MemberService;
import EcoPick.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    private final MemberService memberService;
    private final StoreService storeService;


    public MemberController(MemberService memberService, StoreService storeService) {
        this.memberService = memberService;
        this.storeService = storeService;
    }

    @GetMapping
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMemberbyId(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(memberService.getMemberById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<String> createMember(@RequestBody Member member) {
        memberService.createMember(member);
        return ResponseEntity.status(HttpStatus.CREATED).body("Member created");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id) {
        try {
            memberService.deleteMemberById(id);
            return ResponseEntity.ok("Member deleted");
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/{id}/count")
    @Operation(summary = "회원별 실천 횟수 정보 조회")
    public Integer getUserCount(@PathVariable Long id){
        return memberService.getUserCount(id);
    }

    @GetMapping("/{id}/points")
    @Operation(summary = "회원별 누적 포인트 정보 조회")
    public Integer getUserPoints(@PathVariable Long id){
        return memberService.getUserPoint(id);
    }


}
