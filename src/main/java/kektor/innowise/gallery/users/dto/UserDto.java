package kektor.innowise.gallery.users.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "UserDto",
        description = "User data model returned by the API."
)
public record UserDto(

        @Schema(
                description = "Unique user identifier in the system",
                example = "12345",
                accessMode = Schema.AccessMode.READ_ONLY
        )
        Long id,

        @Schema(
                description = "Unique username (login)",
                example = "alex_white",
                accessMode = Schema.AccessMode.READ_ONLY
        )
        String username,

        @Schema(
                description = "User email address",
                example = "alex.white@example.com",
                format = "email",
                accessMode = Schema.AccessMode.READ_ONLY
        )
        String email) {
}
