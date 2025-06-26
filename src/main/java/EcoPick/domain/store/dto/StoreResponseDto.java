package EcoPick.domain.store.dto;

import EcoPick.domain.store.Store;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StoreResponseDto {
    private final String name;
    private final String address;
    private final String iconSrc;

    public static StoreResponseDto fromEntity(Store store) {
        return new StoreResponseDto(store.getName(), store.getAddress(), store.getIcon_src());
    }
} 