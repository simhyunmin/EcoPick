package EcoPick.controller;


import EcoPick.domain.store.Store;
import EcoPick.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StoreController {

    private StoreService storeService;

    @GetMapping("/store")
    @Operation(summary = "전체 친환경 가게 리스트 조회")
    public List<Store> getStoreList() {
        return storeService.getAllStores();
    }

    @GetMapping("/store/{store_id}")
    @Operation(summary = "각 친환경 가게 정보 조회")
    public Store getStoreById(@PathVariable("store_id") long storeId) {
        return storeService.getStoreById(storeId);
    }

}
