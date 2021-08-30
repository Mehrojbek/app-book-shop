package uz.mehrojbek.appbookshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.mehrojbek.appbookshop.component.UserChecker;
import uz.mehrojbek.appbookshop.enums.PermissionEnum;
import uz.mehrojbek.appbookshop.payload.ApiResult;
import uz.mehrojbek.appbookshop.payload.CustPage;
import uz.mehrojbek.appbookshop.payload.UserDto;
import uz.mehrojbek.appbookshop.payload.UserResp;
import uz.mehrojbek.appbookshop.service.UserService;
import uz.mehrojbek.appbookshop.utils.RestConstants;

import java.util.UUID;

@RestController
@RequestMapping(RestConstants.USER_CONTROLLER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserChecker userChecker;

    @GetMapping
    public ApiResult<CustPage<UserResp>> getAll(@RequestParam(defaultValue = RestConstants.DEFAULT_PAGE) int page,
                                                @RequestParam(defaultValue = RestConstants.DEFAULT_SIZE) int size) {
        userChecker.hasPermission(PermissionEnum.VIEW_USER);
        return userService.getAll(page, size);
    }

    @GetMapping("/{id}")
    public ApiResult<UserResp> getOne(@PathVariable UUID id) {
        return userService.getOne(id);
    }

    @PostMapping
    public ApiResult<?> add(@RequestBody UserDto userDto) {
        userChecker.hasPermission(PermissionEnum.ADD_USER);
        return userService.add(userDto);
    }

    @DeleteMapping("/{id}")
    public ApiResult<?> delete(@PathVariable UUID id) {
        userChecker.hasPermission(PermissionEnum.DELETE_USER);
        return userService.delete(id);
    }

    @PutMapping("/{id}")
    public ApiResult<?> edit(@PathVariable UUID id, @RequestBody UserDto userDto) {
        if (userChecker.getUser().getId().equals(id)) {
            return userService.edit(id, userDto);
        } else {
            userChecker.hasPermission(PermissionEnum.EDIT_USER);
            return userService.edit(id, userDto);
        }
    }
}