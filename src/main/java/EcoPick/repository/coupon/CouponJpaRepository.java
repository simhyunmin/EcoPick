package EcoPick.repository.coupon;

import EcoPick.domain.coupon.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CouponJpaRepository extends JpaRepository<Coupon, Long> {
    List<Coupon> getAllById(List<Long> couponId);
}
