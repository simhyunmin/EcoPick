package EcoPick.domain.member;

import jakarta.persistence.*;
import lombok.*;
import EcoPick.domain.mapping.MemberCoupon;
import EcoPick.domain.mapping.ConnectCompany;
import java.util.List;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String member_name;

    private int point;

    private int count; // 실천행동 누적 횟수

    @OneToMany(mappedBy = "member")
    private List<MemberAddress> addresses;

    @OneToMany(mappedBy = "member")
    private List<MemberCoupon> memberCoupons;

    @OneToMany(mappedBy = "member")
    private List<ConnectCompany> connectCompanies;

    @Column(unique = true)
    private String kakaoId;
}
