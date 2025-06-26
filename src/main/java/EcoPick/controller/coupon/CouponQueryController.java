package EcoPick.controller.coupon;

import EcoPick.domain.coupon.dto.MemberCouponInfoDto;
import EcoPick.service.coupon.CouponQueryServiceImpl;
import EcoPick.service.coupon.ConnectCompanyQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CouponQueryController {

    private final CouponQueryServiceImpl couponQueryService;

    @GetMapping("/members/{memberId}/coupons/info")
    public ResponseEntity<?> getMemberCouponsInfo(@PathVariable Long memberId) {
        try {
            List<MemberCouponInfoDto> memberCouponsInfo = couponQueryService.getMemberCouponsInfo(memberId);
            return ResponseEntity.ok(memberCouponsInfo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("쿠폰 정보 조회 실패: " + e.getMessage());
        }
    }
}
