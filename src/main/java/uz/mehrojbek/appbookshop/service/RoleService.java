package uz.mehrojbek.appbookshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.mehrojbek.appbookshop.entity.Role;
import uz.mehrojbek.appbookshop.exception.RestException;
import uz.mehrojbek.appbookshop.payload.ApiResult;
import uz.mehrojbek.appbookshop.payload.RoleDto;
import uz.mehrojbek.appbookshop.repository.RoleRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;


    public ApiResult<?> getAll(){
        return ApiResult.successRespWithData(roleRepository.findAll());
    }

    public ApiResult<Role> getOne(UUID id){
        Role role = roleRepository.findById(id).orElseThrow(() -> new RestException("role topilmadi", HttpStatus.NOT_FOUND));
        return ApiResult.successRespWithData(role);
    }

    public ApiResult<?> add(RoleDto roleDto){
        try {
            if (roleRepository.existsByName(roleDto.getName()))
                throw new RestException("bu rolallaqachon bor",HttpStatus.CONFLICT);
            Role role = new Role(
                    roleDto.getName(),
                    roleDto.getDescription(),
                    roleDto.getType(),
                    roleDto.getPermissionEnums()
            );
            roleRepository.save(role);
            return ApiResult.successResponse(String.valueOf(role.getId()));
        }catch (Exception e){
            e.printStackTrace();
            throw new RestException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ApiResult<?> delete(UUID id){
        try {
            roleRepository.deleteById(id);
            return ApiResult.successResponse("role o'cirildi");
        }catch (Exception e){
            throw new RestException("role o'chirishda xatolik",HttpStatus.CONFLICT);
        }
    }


    public ApiResult<?> edit(UUID id,RoleDto roleDto){
        if (roleRepository.existsByNameAndIdNot(roleDto.getName(), id))
            throw new RestException("Bunday name li role mavjud",HttpStatus.CONFLICT);
        Role role = roleRepository.findById(id).orElseThrow(() -> new RestException("role topilmadi", HttpStatus.NOT_FOUND));
        role.setName(roleDto.getName());
        role.setDescription(roleDto.getDescription());
        role.setType(roleDto.getType());
        role.setPermissionEnums(roleDto.getPermissionEnums());
        roleRepository.save(role);
        return ApiResult.successResponse("role o'zgartirildi");
    }
}
