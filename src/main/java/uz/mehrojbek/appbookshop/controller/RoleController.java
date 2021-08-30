package uz.mehrojbek.appbookshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.mehrojbek.appbookshop.component.UserChecker;
import uz.mehrojbek.appbookshop.payload.ApiResult;
import uz.mehrojbek.appbookshop.payload.CustPage;
import uz.mehrojbek.appbookshop.payload.RoleDto;
import uz.mehrojbek.appbookshop.payload.RoleResp;
import uz.mehrojbek.appbookshop.service.RoleService;
import uz.mehrojbek.appbookshop.utils.RestConstants;
import java.util.UUID;

import static uz.mehrojbek.appbookshop.enums.PermissionEnum.ADD_ROLE;

@RestController
@RequestMapping(RestConstants.ROLE_CONTROLLER)
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;
    private final UserChecker userChecker;


    @GetMapping
    public ApiResult<CustPage<RoleResp>> getAll(@RequestParam(defaultValue = RestConstants.DEFAULT_PAGE) int page,
                                                @RequestParam(defaultValue = RestConstants.DEFAULT_SIZE) int size){
        return roleService.getAll(page,size);
    }

    @GetMapping("/{id}")
    public ApiResult<RoleResp> getOne(@PathVariable UUID id){
        return roleService.getOne(id);
    }

    @PostMapping
    public ApiResult<?> add(@RequestBody RoleDto roleDto){
        userChecker.hasPermission(ADD_ROLE);
        return roleService.add(roleDto);
    }

    @DeleteMapping("/{id}")
    public ApiResult<?> delete(@PathVariable UUID id){
        return roleService.delete(id);
    }


    @PutMapping("/{id}")
    public ApiResult<?> edit(@PathVariable UUID id, @RequestBody RoleDto roleDto){
        return roleService.edit(id,roleDto);
    }

}
