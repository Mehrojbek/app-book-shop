package uz.mehrojbek.appbookshop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.mehrojbek.appbookshop.entity.template.AbsEntity;
import uz.mehrojbek.appbookshop.enums.PermissionEnum;
import uz.mehrojbek.appbookshop.enums.RoleTypeEnum;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Role extends AbsEntity {
    @Column(nullable = false,unique = true)
    private String name;

    private String description;

    @Enumerated(EnumType.STRING)
    private RoleTypeEnum type;

    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<PermissionEnum> permissionEnums;

    public Role(String name, String description, RoleTypeEnum type) {
        this.name = name;
        this.description = description;
        this.type = type;
    }
}
