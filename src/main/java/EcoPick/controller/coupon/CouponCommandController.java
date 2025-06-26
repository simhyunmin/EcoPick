package EcoPick.controller.coupon;

import EcoPick.service.coupon.CouponCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CouponCommandController {
    private final CouponCommandService couponCommandService;

    @PostMapping("/members/{memberId}/coupons/info")
    public ResponseEntity<?> exchangeCoupon(@PathVariable Long memberId, @RequestParam("couponId") Long couponId) {
        try {
            couponCommandService.exchangeCoupon(memberId, couponId);
            return ResponseEntity.ok("쿠폰 교환 완료");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("쿠폰 교환 실패: " + e.getMessage());
        }
    }
} 