package EcoPick.service;

import EcoPick.domain.company.Company;
import EcoPick.domain.coupon.Coupon;
import EcoPick.domain.member.Member;
import EcoPick.repository.CompanyRepository;
import EcoPick.repository.MemberRepository;
import EcoPick.repository.coupon.CouponJpaRepository;
import EcoPick.repository.coupon.MemberCouponRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberCouponRepository memberCouponRepository;

    public MemberService(MemberRepository memberRepository, MemberCouponRepository memberCouponRepository) {

        this.memberRepository = memberRepository;
        this.memberCouponRepository = memberCouponRepository;
    }

    // member 생성
    public void createMember(Member member){
        memberRepository.save(member);
    }

    // member id로 조회
    public Member getMemberById(Long id){
        return memberRepository.findById(id).get();
    }

    // 모든 member 조회
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    // member 삭제
    public void deleteMemberById(Long id){
        memberRepository.deleteById(id);
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

    // 멤버가 소유한 coupon list 조회
    public List<Coupon> getUserCoupon(Long id){
        Member member = memberRepository.findById(id).get();
        return memberCouponRepository.findCouponsByMemberId(member.getId());
    }

}