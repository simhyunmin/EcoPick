package EcoPick.domain.coupon;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import EcoPick.domain.mapping.MemberCoupon;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int description;

    @Column(nullable = false)
    private LocalDateTime expire_at;

    @OneToMany(mappedBy = "coupon")
    private List<MemberCoupon> memberCoupons;
}


