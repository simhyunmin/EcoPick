package EcoPick.repository.member;

import EcoPick.domain.mapping.ConnectCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ConnectCompanyRepository extends JpaRepository<ConnectCompany, Long> {
    List<ConnectCompany> findByMemberId(Long memberId);
} 