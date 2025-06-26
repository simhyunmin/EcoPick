package EcoPick.repository.member;

import EcoPick.domain.mapping.MemberCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MemberCouponJpaRepository extends JpaRepository<MemberCoupon,Long> {
    public List<Long> getCouponIdByMemberId(Long memberId);
}
