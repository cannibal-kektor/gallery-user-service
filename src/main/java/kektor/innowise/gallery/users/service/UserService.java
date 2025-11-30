package kektor.innowise.gallery.users.service;

import kektor.innowise.gallery.users.dto.RegistrationRequest;
import kektor.innowise.gallery.users.dto.StorePasswordRequest;
import kektor.innowise.gallery.users.dto.UserDto;
import kektor.innowise.gallery.users.exception.EmailExistsException;
import kektor.innowise.gallery.users.exception.UserNotFoundException;
import kektor.innowise.gallery.users.exception.UsernameExistsException;
import kektor.innowise.gallery.users.exception.UsernameNotFoundException;
import kektor.innowise.gallery.users.mapper.UserMapper;
import kektor.innowise.gallery.users.model.User;
import kektor.innowise.gallery.users.repository.UserRepository;
import kektor.innowise.gallery.users.webclient.AuthServiceClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserService {

    final UserRepository userRepository;
    final UserMapper mapper;
    final AuthServiceClient authService;

    @Transactional(readOnly = true)
    public UserDto get(Long id) {
        return userRepository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public UserDto get(String username) {
        return userRepository.findByUsername(username)
                .map(mapper::toDto)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Transactional
    public UserDto register(RegistrationRequest request) {
        checkUserRegistrationRequest(request);
        User user = mapper.toModel(request);
        user = userRepository.save(user);
        authService.storePassword(new StorePasswordRequest(user.getId(), request.password()));
        return mapper.toDto(user);
    }

    private void checkUserRegistrationRequest(RegistrationRequest request) {
        User user = userRepository.findByEmailOrUsername(request.email(), request.username());
        if (user != null) {
            if (user.getUsername().equals(request.username())) {
                throw new UsernameExistsException(user.getUsername());
            } else {
                throw new EmailExistsException(request.email());
            }
        }
    }
}
