package uz.mehrojbek.appbookshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.mehrojbek.appbookshop.payload.ApiResult;
import uz.mehrojbek.appbookshop.payload.UserDto;
import uz.mehrojbek.appbookshop.service.UserService;
import uz.mehrojbek.appbookshop.utils.RestConstants;

import java.util.UUID;

@RestController
@RequestMapping(RestConstants.USER_CONTROLLER)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ApiResult<?> getAll(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public ApiResult<?> getOne(@PathVariable UUID id){
        return userService.getOne(id);
    }

    @PostMapping
    public ApiResult<?> add(@RequestBody UserDto userDto){
        return userService.add(userDto);
    }

    @DeleteMapping("/{id}")
    public ApiResult<?> delete(@PathVariable UUID id){
        return userService.delete(id);
    }

    @PutMapping("/{id}")
    public ApiResult<?> add(@PathVariable UUID id,@RequestBody UserDto userDto){
        return userService.edit(id, userDto);
    }
}
