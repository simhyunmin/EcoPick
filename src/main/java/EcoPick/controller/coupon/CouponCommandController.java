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

    @PostMapping("/members/{id}/coupons")
    public ResponseEntity<?> exchangeCoupon(@PathVariable("id") Long memberId, @RequestParam Long couponId) {
        couponCommandService.exchangeCoupon(memberId, couponId);
        return ResponseEntity.ok("쿠폰 교환 완료");
    }
} 