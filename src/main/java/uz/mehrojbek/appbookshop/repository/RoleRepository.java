package uz.mehrojbek.appbookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mehrojbek.appbookshop.entity.Role;
import uz.mehrojbek.appbookshop.enums.RoleTypeEnum;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, UUID id);
    Role getByType(RoleTypeEnum type);
}
