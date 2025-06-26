package EcoPick.controller.auth;

import EcoPick.domain.member.Member;
import EcoPick.domain.member.dto.KakaoTokenResponse;
import EcoPick.domain.member.dto.KakaoUserInfo;
import EcoPick.service.KakaoOAuthHandler;
import EcoPick.service.MemberCommandService;
import EcoPick.service.MemberQueryService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@RestController
@Slf4j
public class KakaoAuthController {

    private final MemberQueryService memberQueryService;
    private final MemberCommandService memberCommandService;

    @Value("${kakao.client-id}")
    private String clientId;
    @Value("${kakao.redirect-uri}")
    private String redirectUri;
    @Value("${kakao.response-type}")
    private String responseType;

    public KakaoAuthController(MemberQueryService memberQueryService,
                               MemberCommandService memberCommandService) {
        this.memberQueryService = memberQueryService;
        this.memberCommandService = memberCommandService;
    }

    @GetMapping("/oauth/kakao/authorize")
    public void joinKakaoMember(HttpServletResponse response) throws IOException {
        String kakaoAuthUrl = "https://kauth.kakao.com/oauth/authorize"
                + "?client_id=" + clientId
                + "&redirect_uri=" + redirectUri
                + "&response_type=" + responseType;
        log.info("[카카오 로그인] 인증 요청 URL: {}", kakaoAuthUrl);
        response.sendRedirect(kakaoAuthUrl);
    }

    @GetMapping("/login/oauth2/code/kakao")
    public void processCallback(@RequestParam("code") String code,
                                HttpServletRequest request,
                                HttpServletResponse response) throws IOException {
        log.info("[카카오 로그인] 콜백 도착, 인가코드: {}", code);
        KakaoTokenResponse tokenResponse = KakaoOAuthHandler.requestToken(code, clientId, redirectUri);
        log.info("[카카오 로그인] 토큰 응답: {}", tokenResponse);
        KakaoUserInfo userInfo = KakaoOAuthHandler.requestUserInfo(tokenResponse.getAccess_token());
        log.info("[카카오 로그인] 사용자 정보: {}", userInfo);
        Optional<Member> existingMemberOpt = memberQueryService.findByKakaoId(userInfo.getId());
        Member member = existingMemberOpt.orElseGet(() -> {
            log.info("[카카오 로그인] 신규 회원 생성: {}", userInfo.getId());
            return memberCommandService.createKakaoMember(userInfo);
        });
        log.info("[카카오 로그인] 최종 회원 객체: {}", member);
        HttpSession session = request.getSession(true);
        session.setMaxInactiveInterval(3600); // 1시간
        session.setAttribute("member", member);
        session.setAttribute("userInfo", userInfo);
        log.info("[카카오 로그인] 세션 저장 완료, /api/company로 리다이렉트");
        response.sendRedirect("/api/company");
    }

    @PostMapping("/api/v1/topic/additional")
    public void setMBTI(HttpServletRequest request, HttpServletResponse response,
                        @RequestParam("mbti") String mbtiValue) throws IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("member") == null) {
            response.sendRedirect("/oauth/kakao/authorize");
            return;
        }
        Member member = (Member) session.getAttribute("member");
        memberCommandService.setKakaoMember(member);
        session.setAttribute("member", member);
        response.sendRedirect("/api/company");
    }
} 