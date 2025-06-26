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
@RequestMapping("/api/")
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




//    @GetMapping("/{energy-type}/getList")
//    @Operation(summary = "특정 기간의 에너지 데이터 조회")
//    public List<ElectricityData> getDataList(
//            @PathVariable(name = "energy-type") EnergyType energyType,
//            @RequestParam LocalDateTime start,
//            @RequestParam LocalDateTime end,
//            @RequestParam String measurement) {
//        logger.info("Fetching electricity data between: " + start + " and " + end + " for measurement: " + measurement + " and tag: " + energyType.name());
//        return influxService.readDataList(start, end, fromUrlBuildingNameToEnglish(measurement), energyType);
//    }
}
