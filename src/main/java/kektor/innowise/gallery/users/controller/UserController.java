package kektor.innowise.gallery.users.controller;

import jakarta.validation.Valid;
import kektor.innowise.gallery.security.UserPrincipal;
import kektor.innowise.gallery.users.controller.openapi.UserServiceOpenApi;
import kektor.innowise.gallery.users.dto.RegistrationRequest;
import kektor.innowise.gallery.users.dto.UserDto;
import kektor.innowise.gallery.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController implements UserServiceOpenApi {

    final UserService userService;

    @PostMapping(
            path = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public ResponseEntity<UserDto> register(@RequestBody @Valid RegistrationRequest registrationRequest) {
        return ResponseEntity.ok()
                .body(userService.register(registrationRequest));
    }

    @GetMapping(
            path = "/id/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(userService.get(id));
    }

    @GetMapping(
            path = "/username/{username}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public ResponseEntity<UserDto> getByUsername(@PathVariable String username) {
        UserDto user = userService.get(username);
        return ResponseEntity.ok(user);
    }

    @GetMapping(
            path = "/current",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @Override
    public ResponseEntity<UserDto> getCurrent(@AuthenticationPrincipal UserPrincipal principal) {
        UserDto user = userService.get(principal.username());
        return ResponseEntity.ok(user);
    }

}
