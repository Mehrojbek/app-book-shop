package uz.mehrojbek.appbookshop.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.mehrojbek.appbookshop.entity.User;
import uz.mehrojbek.appbookshop.exception.RestException;
import uz.mehrojbek.appbookshop.payload.ApiResult;
import uz.mehrojbek.appbookshop.payload.UserDto;
import uz.mehrojbek.appbookshop.repository.RoleRepository;
import uz.mehrojbek.appbookshop.repository.UserRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public ApiResult<?> getAll(){
        return ApiResult.successRespWithData(userRepository.findAll());
    }

    public ApiResult<User> getOne(UUID id){
        User user = userRepository.findById(id).orElseThrow(() -> new RestException("user topilmadi", HttpStatus.NOT_FOUND));
        return ApiResult.successRespWithData(user);
    }

    public ApiResult<?> add(UserDto userDto){
        if (userRepository.existsByUsername(userDto.getUsername()))
            throw new RestException("bu usernameli user mavjud",HttpStatus.CONFLICT);
        userRepository.save(new User(
                userDto.getFullName(),
                userDto.getUsername(),
                passwordEncoder.encode(userDto.getPassword()),
                roleRepository.findById(userDto.getRoleId()).orElseThrow(() -> new RestException("role topilmadi",HttpStatus.NOT_FOUND)),
                true
        ));
        return ApiResult.successResponse("user saqlandi");
    }

    public ApiResult<?> delete(UUID id){
        try {
            userRepository.deleteById(id);
            return ApiResult.successResponse("user o;chirildi");
        }catch (Exception e){
            e.printStackTrace();
            throw new RestException("userni o'chirishda xatolik",HttpStatus.CONFLICT);
        }
    }

    public ApiResult<?> edit(UUID id, UserDto userDto){
        try {
            User user = userRepository.findById(id).orElseThrow(() -> new RestException("user topilmadi", HttpStatus.NOT_FOUND));
            if (userRepository.existsByUsernameAndIdNot(userDto.getUsername(), id))
                throw new RestException("bunday usernameli user mavjud", HttpStatus.CONFLICT);
            user.setFullName(userDto.getFullName());
            user.setUsername(userDto.getUsername());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setRole(roleRepository.findById(userDto.getRoleId()).orElseThrow(() -> new RestException("role topilmadi", HttpStatus.NOT_FOUND)));
            userRepository.save(user);
            return ApiResult.successResponse("user o'zgartirildi");
        }catch (Exception e){
            e.printStackTrace();
            throw new RestException(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
