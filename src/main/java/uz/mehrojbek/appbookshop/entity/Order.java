package uz.mehrojbek.appbookshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.mehrojbek.appbookshop.entity.template.AbsEntity;
import uz.mehrojbek.appbookshop.enums.ProductEnum;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Order extends AbsEntity {
    @ManyToOne
    private Client client;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<ProductEnum> productList;
}
