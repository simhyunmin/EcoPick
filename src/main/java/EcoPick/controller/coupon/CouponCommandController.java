package EcoPick.controller.coupon;

import EcoPick.service.coupon.CouponCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import EcoPick.domain.member.Member;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CouponCommandController {
    private final CouponCommandService couponCommandService;

    @PostMapping("/me/coupons/info")
    public ResponseEntity<?> exchangeCoupon(HttpServletRequest request, @RequestParam("couponId") Long couponId) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("member") == null) {
            return ResponseEntity.status(401).body("로그인이 필요합니다.");
        }
        Member member = (Member) session.getAttribute("member");
        couponCommandService.exchangeCoupon(member.getId(), couponId);
        return ResponseEntity.ok("쿠폰 교환 완료");
    }
} 