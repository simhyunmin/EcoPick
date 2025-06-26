package EcoPick.repository.member;

import EcoPick.domain.mapping.MemberCoupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MemberCouponJpaRepository extends JpaRepository<MemberCoupon, Long> {
    List<MemberCoupon> findByMemberId(Long memberId);
}
