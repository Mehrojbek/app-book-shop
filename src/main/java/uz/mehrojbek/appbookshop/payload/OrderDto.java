package uz.mehrojbek.appbookshop.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.mehrojbek.appbookshop.enums.ProductEnum;

import java.util.List;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDto {
    private UUID id;

    private List<ProductEnum> productList;
}
