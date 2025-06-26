package EcoPick.controller.coupon;

import EcoPick.domain.coupon.dto.MemberCouponInfoDto;
import EcoPick.service.coupon.CouponQueryServiceImpl;
import EcoPick.service.coupon.ConnectCompanyQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CouponQueryController {

    private final CouponQueryServiceImpl couponQueryService;
    private final ConnectCompanyQueryService connectCompanyQueryService;

    @GetMapping("/members/{id}/coupons/info")
    public ResponseEntity<?> getMemberCouponsInfo(@PathVariable("id")Long memberId) {
        List<MemberCouponInfoDto> memberCouponsInfo = couponQueryService.getMemberCouponsInfo(memberId);
        return ResponseEntity.ok(memberCouponsInfo);
    }

    @GetMapping("/members/{id}/companies")
    public ResponseEntity<?> getSubscribedCompanies(@PathVariable("id") Long memberId) {
        return ResponseEntity.ok(connectCompanyQueryService.getCompaniesByMemberId(memberId));
    }
}
