package EcoPick.controller;

import EcoPick.domain.company.Company;
import EcoPick.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class CompanyController {

    private CompanyService companyService;

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
}