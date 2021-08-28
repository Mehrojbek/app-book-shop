package uz.mehrojbek.appbookshop.payload;

import lombok.Data;
import uz.mehrojbek.appbookshop.enums.PermissionEnum;
import uz.mehrojbek.appbookshop.enums.RoleTypeEnum;
import java.util.List;

@Data
public class RoleDto {
    private String name;

    private String description;

    private RoleTypeEnum type;

    private List<PermissionEnum> permissionEnums;
}
