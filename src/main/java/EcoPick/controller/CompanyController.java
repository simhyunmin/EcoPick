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

    @GetMapping("/members/{memberId}/companies")
    public ResponseEntity<?> getSubscribedCompanies(@PathVariable Long memberId) {
        try {
            return ResponseEntity.ok(connectCompanyQueryService.getCompaniesByMemberId(memberId));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("구독 기업 조회 실패: " + e.getMessage());
        }
    }
}