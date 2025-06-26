package EcoPick.service.coupon;

import EcoPick.domain.coupon.Coupon;
import EcoPick.domain.mapping.MemberCoupon;
import EcoPick.domain.member.Member;
import EcoPick.repository.coupon.CouponJpaRepository;
import EcoPick.repository.member.MemberCouponJpaRepository;
import EcoPick.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CouponCommandService {
    private final MemberRepository memberRepository;
    private final CouponJpaRepository couponJpaRepository;
    private final MemberCouponJpaRepository memberCouponJpaRepository;

    @Transactional
    public void exchangeCoupon(Long memberId, Long couponId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
        Coupon coupon = couponJpaRepository.findById(couponId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 쿠폰입니다."));

        MemberCoupon memberCoupon = MemberCoupon.builder()
                .member(member)
                .coupon(coupon)
                .build();

        memberCouponJpaRepository.save(memberCoupon);
    }
} 