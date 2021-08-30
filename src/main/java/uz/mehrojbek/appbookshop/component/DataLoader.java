package uz.mehrojbek.appbookshop.component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.mehrojbek.appbookshop.entity.User;
import uz.mehrojbek.appbookshop.repository.RoleRepository;
import uz.mehrojbek.appbookshop.entity.Role;
import uz.mehrojbek.appbookshop.enums.PermissionEnum;
import uz.mehrojbek.appbookshop.enums.RoleTypeEnum;
import uz.mehrojbek.appbookshop.repository.UserRepository;

import java.util.Arrays;

import static uz.mehrojbek.appbookshop.enums.PermissionEnum.*;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.sql.init.mode}")
    private String initMode;

    @Override
    public void run(String... args) throws Exception {
        if (initMode.equals("always")){
            for (RoleTypeEnum roleEnum : RoleTypeEnum.values()) {
                Role role = new Role(
                        roleEnum.name(),
                        roleEnum.name(),
                        roleEnum
                );
                switch (roleEnum){
                    case ADMIN:
                        role.setPermissionEnums(Arrays.asList(PermissionEnum.values()));
                        break;
                    case OPERATOR:
                        role.setPermissionEnums(Arrays.asList(VIEW_CLIENT,ADD_CLIENT,EDIT_CLIENT));
                }
                roleRepository.save(role);
            }
            //add oper, admin user
            userRepository.save(
                    new User(
                            "admin",
                            "admin",
                            passwordEncoder.encode("admin123"),
                            roleRepository.getByType(RoleTypeEnum.ADMIN),
                            true
                    )
            );
            userRepository.save(
                    new User(
                            "operator",
                            "operator",
                            passwordEncoder.encode( "oper123"),
                            roleRepository.getByType(RoleTypeEnum.OPERATOR),
                            true
                    )
            );
        }
    }
}
