package EcoPick.service.coupon;

import EcoPick.domain.coupon.Coupon;
import EcoPick.domain.coupon.dto.MemberCouponInfoDto;
import EcoPick.domain.mapping.MemberCoupon;
import EcoPick.repository.coupon.CouponJpaRepository;
import EcoPick.repository.member.MemberCouponJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CouponQueryServiceImpl {
    private final CouponJpaRepository couponJpaRepository;
    private final MemberCouponJpaRepository memberCouponJpaRepository;

    public List<MemberCouponInfoDto> getMemberCouponsInfo(Long memberId) {
        List<MemberCoupon> memberCoupons = memberCouponJpaRepository.findByMemberId(memberId);
        List<Long> couponIds = memberCoupons.stream()
                .map(mc -> mc.getCoupon().getId())
                .collect(Collectors.toList());
        return couponJpaRepository.findAllByIdIn(couponIds).stream()
                .map(coupon -> new MemberCouponInfoDto(
                        coupon.getPrice()
                        , coupon.getDescription()
                        , coupon.getExpire_at()))
                .collect(Collectors.toList());
    }
}
