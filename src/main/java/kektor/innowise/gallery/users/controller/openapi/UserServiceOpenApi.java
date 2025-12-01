package kektor.innowise.gallery.users.controller.openapi;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.FailedApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kektor.innowise.gallery.security.UserPrincipal;
import kektor.innowise.gallery.users.dto.RegistrationRequest;
import kektor.innowise.gallery.users.dto.UserDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static kektor.innowise.gallery.users.config.OpenApiConfig.INTERNAL_SERVICE_AUTH;
import static kektor.innowise.gallery.users.config.OpenApiConfig.JWT_BEARER_TOKEN;
import static kektor.innowise.gallery.users.config.OpenApiConfig.PROBLEM_DETAIL_RESPONSE;

@Tag(
        name = "User Management API",
        description = "Provides API for registering new users and retrieving information about existing users."
)
@FailedApiResponse(ref = PROBLEM_DETAIL_RESPONSE)
public interface UserServiceOpenApi {

    @Operation(
            summary = "Register new user",
            description = "Creates a new user in the system.",
            requestBody = @RequestBody(
                    description = "User registration data",
                    required = true,
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RegistrationRequest.class)
                    )
            )
    )
    @UserDtoResponse
    ResponseEntity<UserDto> register(@Valid RegistrationRequest registrationRequest);

    @Operation(
            summary = "Get user by ID",
            description = "Retrieves user information by their id",
            parameters = @Parameter(
                    name = "id",
                    description = "Unique user identifier",
                    required = true,
                    schema = @Schema(
                            type = "integer",
                            format = "int64",
                            minimum = "1",
                            example = "12345"
                    )
            ),
            security = @SecurityRequirement(name = INTERNAL_SERVICE_AUTH)
    )
    @UserDtoResponse
    ResponseEntity<UserDto> getById(Long id);

    @Operation(
            summary = "Get user by username",
            description = "Retrieves user information by their unique username",
            parameters = @Parameter(
                    name = "username",
                    description = "Unique username (login)",
                    required = true,
                    schema = @Schema(
                            type = "string",
                            pattern = "^[a-z0-9_-]{3,30}$",
                            example = "alex_white",
                            minLength = 3,
                            maxLength = 30
                    )
            ),
            security = @SecurityRequirement(name = INTERNAL_SERVICE_AUTH)

    )
    @UserDtoResponse
    ResponseEntity<UserDto> getByUsername(String username);


    @Operation(
            summary = "Get current authenticated user",
            description = "Retrieves information about the currently authenticated user.",
            security = @SecurityRequirement(name = JWT_BEARER_TOKEN)
    )
    @UserDtoResponse
    ResponseEntity<UserDto> getCurrent(UserPrincipal principal);

}
