package uz.mehrojbek.appbookshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.mehrojbek.appbookshop.entity.Role;
import uz.mehrojbek.appbookshop.exception.RestException;
import uz.mehrojbek.appbookshop.payload.*;
import uz.mehrojbek.appbookshop.repository.RoleRepository;

import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;


    public ApiResult<CustPage<RoleResp>> getAll(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Role> rolePage = roleRepository.findAll(pageable);
        CustPage<RoleResp> custPage = custPageBuilder(rolePage);
        custPage.setContent(rolePage.getContent().stream().map(this::roleRespBuilder).collect(Collectors.toList()));
        return ApiResult.successRespWithData(custPage);
    }

    public ApiResult<RoleResp> getOne(UUID id){
        Role role = roleRepository.findById(id).orElseThrow(() -> new RestException("role topilmadi", HttpStatus.NOT_FOUND));
        return ApiResult.successRespWithData(roleRespBuilder(role));
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

    private CustPage<RoleResp> custPageBuilder(Page<Role> page){
        return new CustPage<>(
                page.getNumber(),
                page.getNumberOfElements(),
                page.getSize(),
                page.getSort(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.getPageable()
        );
    }

    private RoleResp roleRespBuilder(Role role){
        return new RoleResp(
                role.getId(),
                role.getName(),
                role.getDescription(),
                role.getType(),
                role.getPermissionEnums()
        );
    }
}
