package uz.mehrojbek.appbookshop.payload;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {

    private String fullName;

    private String username;

    private String password;

    private UUID roleId;
}
