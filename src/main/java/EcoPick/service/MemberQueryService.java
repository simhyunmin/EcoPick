package EcoPick.service;

import EcoPick.domain.member.Member;
import EcoPick.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberQueryService {
    @Autowired
    private MemberRepository memberRepository;

    public Optional<Member> findByKakaoId(String kakaoId) {
        return memberRepository.findByKakaoId(kakaoId);
    }

    public Optional<Member> findById(Long id) {
        return memberRepository.findById(id);
    }
} 