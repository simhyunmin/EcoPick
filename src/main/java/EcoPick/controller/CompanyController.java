package EcoPick.controller;

import EcoPick.domain.company.Company;
import EcoPick.service.CompanyService;
import EcoPick.service.coupon.ConnectCompanyQueryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import EcoPick.domain.member.Member;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CompanyController {

    private final CompanyService companyService;
    private final ConnectCompanyQueryService connectCompanyQueryService;

    @GetMapping("/company")
    @Operation(summary = "전체 친환경 기업 리스트 조회")
    public List<Company> getCompanyList() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/company/{company-id}")
    @Operation(summary = "각 친환경 기업 정보 조회")
    public Company getCompanybyId(@PathVariable(name = "company-id") Long companyId) {
        return companyService.getCompanyById(companyId);
    }

    @GetMapping("/me/companies")
    public ResponseEntity<?> getSubscribedCompanies(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("member") == null) {
            return ResponseEntity.status(401).body("로그인이 필요합니다.");
        }
        Member member = (Member) session.getAttribute("member");
        return ResponseEntity.ok(connectCompanyQueryService.getCompaniesByMemberId(member.getId()));
    }
}