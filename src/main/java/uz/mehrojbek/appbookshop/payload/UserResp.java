package uz.mehrojbek.appbookshop.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.mehrojbek.appbookshop.entity.Role;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResp {
    private UUID id;
    private String fullName;
    private String username;
    private RoleResp role;
    private boolean enabled;
}
