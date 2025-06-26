package EcoPick.service;

import EcoPick.domain.member.Member;
import EcoPick.dto.KakaoUserInfo;
import EcoPick.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberCommandService {
    @Autowired
    private MemberRepository memberRepository;

    public Member createKakaoMember(KakaoUserInfo userInfo) {
        String kakaoId = userInfo.getId();
        String nickname = "카카오사용자";
        if (userInfo.getProperties() != null && userInfo.getProperties().get("nickname") != null) {
            nickname = userInfo.getProperties().get("nickname").toString();
        }
        Member member = Member.builder()
                .kakaoId(kakaoId)
                .member_name(nickname)
                .point(0)
                .build();
        return memberRepository.save(member);
    }

    

    public Member setKakaoMember(Member member) {
        return memberRepository.save(member);
    }

} 