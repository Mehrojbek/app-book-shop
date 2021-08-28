package uz.mehrojbek.appbookshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.mehrojbek.appbookshop.entity.template.AbsEntity;
import uz.mehrojbek.appbookshop.enums.RegionEnum;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Client extends AbsEntity {
    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private RegionEnum region;

    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
    private List<Order> orderList;

    public Client(String fullName, String phoneNumber, RegionEnum region) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.region = region;
    }
}
