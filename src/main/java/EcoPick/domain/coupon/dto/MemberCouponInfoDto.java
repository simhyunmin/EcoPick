package EcoPick.domain.coupon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class MemberCouponInfoDto {
    private int price;
    private int description;
    private LocalDateTime expire_at;
}
