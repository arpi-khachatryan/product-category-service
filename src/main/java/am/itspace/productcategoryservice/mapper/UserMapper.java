package am.itspace.productcategoryservice.mapper;

import am.itspace.productcategoryservice.dto.CreateUserDto;
import am.itspace.productcategoryservice.dto.UserResponceDto;
import am.itspace.productcategoryservice.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
//
//    @Mapping(target = "role", defaultValue = "USER")
    User mapToEntity(CreateUserDto createUserDto);

    UserResponceDto map(User user);
}
