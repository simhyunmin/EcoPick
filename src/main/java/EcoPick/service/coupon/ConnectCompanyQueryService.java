package EcoPick.service.coupon;

import EcoPick.domain.company.Company;
import EcoPick.domain.mapping.ConnectCompany;
import EcoPick.repository.member.ConnectCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ConnectCompanyQueryService {
    private final ConnectCompanyRepository connectCompanyRepository;

    public List<CompanyInfoDto> getCompaniesByMemberId(Long memberId) {
        List<ConnectCompany> connectCompanies = connectCompanyRepository.findByMemberId(memberId);
        return connectCompanies.stream()
                .map(cc -> new CompanyInfoDto(cc.getCompany().getName(), cc.getCompany().getIcon_src()))
                .collect(Collectors.toList());
    }

    public record CompanyInfoDto(String name, String icon_src) {}
} 