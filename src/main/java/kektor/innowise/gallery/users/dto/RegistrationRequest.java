package kektor.innowise.gallery.users.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(
        name = "RegistrationRequest",
        description = "Request model for registering a new user in the Image Gallery system"
)
public record RegistrationRequest(

        @Schema(
                description = "Unique username (login)",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "alex_white",
                minLength = 3,
                maxLength = 30,
                pattern = "^[a-z0-9_-]{3,30}$"
        )
        @NotBlank
        @Size(min = 3, max = 30)
        @Pattern(regexp = "^[a-z0-9_-]{3,30}$", flags = Pattern.Flag.CASE_INSENSITIVE)
        String username,

        @Schema(
                description = "User password.",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "testPassword123",
                minLength = 8,
                maxLength = 50,
                format = "password"
        )
        @NotBlank
        @Size(min = 8, max = 50)
        String password,

        @Schema(
                description = "User email address. Must be unique in the system",
                requiredMode = Schema.RequiredMode.REQUIRED,
                example = "alex.white@example.com",
                format = "email",
                pattern = "^[a-z0-9._-]+@[a-z0-9.-]+\\.[a-z]{2,}$"
        )
        @NotNull
        @Email(regexp = "^[a-z0-9._-]+@[a-z0-9.-]+\\.[a-z]{2,}$", flags = Pattern.Flag.CASE_INSENSITIVE)
        String email
) {
}
