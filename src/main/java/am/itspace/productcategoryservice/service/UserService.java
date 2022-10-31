package am.itspace.productcategoryservice.service;

import am.itspace.productcategoryservice.dto.CreateUserDto;
import am.itspace.productcategoryservice.dto.UserAuthDto;
import am.itspace.productcategoryservice.dto.UserAuthResponceDto;
import am.itspace.productcategoryservice.dto.UserResponceDto;

public interface UserService {

    UserResponceDto save(CreateUserDto createUserDto);

    UserAuthResponceDto authentication(UserAuthDto userAuthDto);
}
