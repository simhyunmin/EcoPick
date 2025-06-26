package EcoPick.controller;


import EcoPick.domain.store.dto.StoreResponseDto;
import EcoPick.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class StoreController {

    private final StoreService storeService;

    @GetMapping("/stores")
    @Operation(summary = "전체 친환경 가게 리스트 조회")
    public List<StoreResponseDto> getStoreList() {
        return storeService.getAllStores().stream()
                .map(StoreResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/store/{store_id}")
    @Operation(summary = "각 친환경 가게 정보 조회")
    public StoreResponseDto getStoreById(@PathVariable("store_id") long storeId) {
        return StoreResponseDto.fromEntity(storeService.getStoreById(storeId));
    }

}
