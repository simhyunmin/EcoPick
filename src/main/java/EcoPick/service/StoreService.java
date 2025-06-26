package EcoPick.service;

import EcoPick.domain.company.store.Store;
import EcoPick.repository.StoreRepository;
import io.swagger.v3.oas.annotations.servers.Server;

import java.util.List;

@Server
public class StoreService {

    private final StoreRepository storeRepository;

    public StoreService(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    public Store getStoreById(Long id) {
        return storeRepository.findById(id).orElse(null);
    }


}
