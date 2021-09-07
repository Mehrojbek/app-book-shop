package uz.mehrojbek.appbookshop.payload;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import uz.mehrojbek.appbookshop.enums.PermissionEnum;
import uz.mehrojbek.appbookshop.enums.RoleTypeEnum;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class RoleDto {
    @NotBlank
    private String name;

    private String description;

    @ApiModelProperty(example = "ADMIN, OPERATOR, CUSTOM(adminni o'zi yaratgan role)")
    private RoleTypeEnum type;

    @ApiModelProperty(example = "[VIEW_ROLE,ADD_ROLE,EDIT_ROLE,DELETE_ROLE,VIEW_ORDER,ADD_ORDER,EDIT_ORDER,DELETE_ORDER,VIEW_USER,ADD_USER,EDIT_USER,DELETE_USER]")
    private List<PermissionEnum> permissionEnums;
}
