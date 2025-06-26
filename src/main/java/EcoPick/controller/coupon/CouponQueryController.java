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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import EcoPick.domain.member.Member;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CouponQueryController {

    private final CouponQueryServiceImpl couponQueryService;

    @GetMapping("/me/coupons/info")
    public ResponseEntity<?> getMemberCouponsInfo(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("member") == null) {
            return ResponseEntity.status(401).body("로그인이 필요합니다.");
        }
        Member member = (Member) session.getAttribute("member");
        List<MemberCouponInfoDto> memberCouponsInfo = couponQueryService.getMemberCouponsInfo(member.getId());
        return ResponseEntity.ok(memberCouponsInfo);
    }
}
