package uz.mehrojbek.appbookshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import uz.mehrojbek.appbookshop.component.UserChecker;
import uz.mehrojbek.appbookshop.enums.PermissionEnum;
import uz.mehrojbek.appbookshop.payload.ApiResult;
import uz.mehrojbek.appbookshop.payload.ClientDto;
import uz.mehrojbek.appbookshop.payload.CustPage;
import uz.mehrojbek.appbookshop.service.ClientService;
import uz.mehrojbek.appbookshop.utils.RestConstants;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(RestConstants.CLIENT_CONTROLLER)
@RequiredArgsConstructor
public class ClientController {
    private final ClientService clientService;
    private final UserChecker userChecker;

    @GetMapping
    public ApiResult<CustPage<ClientDto>> getAll(@RequestParam(defaultValue = RestConstants.DEFAULT_PAGE) int page,
                                                 @RequestParam(defaultValue = RestConstants.DEFAULT_SIZE) int size){
        userChecker.hasPermission(PermissionEnum.VIEW_CLIENT);
        return clientService.getAll(page, size);
    }

    @GetMapping("/{id}")
    public ApiResult<ClientDto> getOne(@PathVariable UUID id){
        return clientService.getOne(id);
    }

    @PostMapping
    public ApiResult<?> add(@RequestBody @Valid ClientDto clientDto){
        userChecker.hasPermission(PermissionEnum.ADD_CLIENT);
        return clientService.add(clientDto);
    }

    @DeleteMapping("/{id}")
    public ApiResult<?> delete(@PathVariable UUID id){
        userChecker.hasPermission(PermissionEnum.DELETE_CLIENT);
        return clientService.delete(id);
    }

    @PutMapping("/{id}")
    public ApiResult<?> edit(@PathVariable UUID id, @RequestBody @Valid ClientDto clientDto){
        userChecker.hasPermission(PermissionEnum.EDIT_CLIENT);
        return clientService.edit(id, clientDto);
    }
}
