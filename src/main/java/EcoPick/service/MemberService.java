package EcoPick.service;

import EcoPick.domain.coupon.Coupon;
import EcoPick.domain.member.Member;
import EcoPick.repository.MemberRepository;
import EcoPick.repository.coupon.CouponJpaRepository;
import EcoPick.repository.member.MemberCouponJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberCouponJpaRepository memberCouponJpaRepository;

    // member 생성
    public void createMember(Member member){
        memberRepository.save(member);
    }

    // member id로 조회
    public Member getMemberById(Long id){
        return memberRepository.findById(id).get();
    }

    // member 삭제
    public void deleteMemberById(Long id){
        memberRepository.deleteById(id);
    }
    public Member findOrCreateByKakaoId(String kakaoId, java.util.Map<String, Object> attributes) {
        return memberRepository.findByKakaoId(kakaoId)
                .orElseGet(() -> {
                    String name = attributes.getOrDefault("nickname", "카카오사용자").toString();
                    Member member = Member.builder()
                            .kakaoId(kakaoId)
                            .member_name(name)
                            .point(0)
                            .build();
                    return memberRepository.save(member);
                });
    }

    // count 조회
    public int getUserCount(Long id){
        Member member = memberRepository.findById(id).get();
        return member.getCount();
    }

    // point 조회
    public int getUserPoint(Long id){
        Member member = memberRepository.findById(id).get();
        return member.getPoint();
    }

}