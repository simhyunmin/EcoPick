package EcoPick.service;

import EcoPick.domain.company.Company;
import EcoPick.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    public Company getCompanyById(Long companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }


}



//public ClientService(ClientRepository clientRepository) {
//    this.clientRepository = clientRepository;
//}
//
//public Client getClientById(Long clientId){
//    return clientRepository.findById(clientId).get();
//}
//
//public Client updateTargetUsage(Long clientId, Double targetUsage){
//    Optional<Client> result = clientRepository.findById(clientId);
//    if(result.isEmpty())
//        return null;
//    Client client = result.get();
//    client.setTargetUsage(targetUsage);
//    return clientRepository.save(client);
//}
