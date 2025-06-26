package EcoPick.repository.coupon;

import EcoPick.domain.coupon.Coupon;
import EcoPick.domain.mapping.MemberCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberCouponRepository extends JpaRepository<MemberCoupon, Long> {

    // member id로 연결된 coupon 연결
    List<Coupon> findCouponsByMemberId(@Param("memberId") Long memberId);
}
