package EcoPick.service;

import EcoPick.domain.member.Member;
import EcoPick.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
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


}