package kektor.innowise.gallery.users.mapper;

import kektor.innowise.gallery.users.dto.RegistrationRequest;
import kektor.innowise.gallery.users.dto.UserDto;
import kektor.innowise.gallery.users.model.User;
import org.mapstruct.Mapper;

@Mapper(config = MapConfig.class)
public interface UserMapper {

    User toModel(RegistrationRequest registrationRequest);

    UserDto toDto(User user);

}
