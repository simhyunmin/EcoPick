package EcoPick.service.coupon;

import EcoPick.domain.coupon.Coupon;
import EcoPick.domain.coupon.dto.MemberCouponInfoDto;
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
    private CouponJpaRepository couponJpaRepository;
    private MemberCouponJpaRepository memberCouponJpaRepository;

    public List<MemberCouponInfoDto> getMemberCouponsInfo(Long memberId) {
        List<Long> couponIdByMemberId = memberCouponJpaRepository.getCouponIdByMemberId(memberId);
        return couponJpaRepository.getAllById(couponIdByMemberId).stream()
                .map(coupon -> new MemberCouponInfoDto(
                        coupon.getPrice()
                        , coupon.getDescription()
                        , coupon.getExpire_at()))
                .collect(Collectors.toList());
    }
}
