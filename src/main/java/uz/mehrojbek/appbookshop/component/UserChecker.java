package uz.mehrojbek.appbookshop.component;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import uz.mehrojbek.appbookshop.entity.User;
import uz.mehrojbek.appbookshop.enums.PermissionEnum;
import uz.mehrojbek.appbookshop.exception.RestException;

@Component
public class UserChecker {
    public User getUser(){
        try {
            return  (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch (Exception e){
            throw new RestException("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
        }
    }

    public boolean hasPermission(PermissionEnum permissionEnum){
        User user = getUser();
        if (user == null)
            throw new RestException("UNAUTHORIZED", HttpStatus.UNAUTHORIZED);
        return user.getRole().getPermissionEnums().contains(permissionEnum);
    }
}
