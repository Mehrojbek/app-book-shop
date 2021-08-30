package uz.mehrojbek.appbookshop.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.mehrojbek.appbookshop.enums.PermissionEnum;
import uz.mehrojbek.appbookshop.enums.RoleTypeEnum;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleResp {
    private UUID id;
    private String name;
    private String description;

    private RoleTypeEnum type;

    private List<PermissionEnum> permissionEnums;
}
