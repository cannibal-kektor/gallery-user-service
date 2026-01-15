package kektor.innowise.gallery.users.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegistrationRequest(
        @NotBlank
        @Size(min = 3, max = 30)
        @Pattern(regexp = "^[a-z0-9_-]{3,30}$", flags = Pattern.Flag.CASE_INSENSITIVE)
        String username,
        @NotBlank
        @Size(min = 8, max = 50)
        String password,
        @NotNull
        @Email(regexp = "^[a-z0-9._-]+@[a-z0-9.-]+\\.[a-z]{2,}$", flags = Pattern.Flag.CASE_INSENSITIVE)
        String email) {
}
