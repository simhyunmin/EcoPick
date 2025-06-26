package EcoPick.repository.coupon;

import EcoPick.domain.coupon.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CouponJpaRepository extends JpaRepository<Coupon, Long> {
    List<Coupon> findAllByIdIn(List<Long> ids);
}
