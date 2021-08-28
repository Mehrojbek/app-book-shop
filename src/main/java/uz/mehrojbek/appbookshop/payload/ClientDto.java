package uz.mehrojbek.appbookshop.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.mehrojbek.appbookshop.enums.RegionEnum;


import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private UUID id;

    private String fullName;

    private String phoneNumber;

    private RegionEnum region;

    private List<OrderDto> orderList;
}
