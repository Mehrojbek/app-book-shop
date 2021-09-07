package uz.mehrojbek.appbookshop.payload;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {
    @ApiModelProperty(example = "G'anivoy G'aniyev")
    private String fullName;

    @ApiModelProperty(example = "g'ani1200")
    private String username;

    private String password;

    @ApiModelProperty(example = "4uba-acxd-24nbjnjk_bkb")
    private UUID roleId;
}
