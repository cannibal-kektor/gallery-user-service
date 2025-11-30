package kektor.innowise.gallery.users.dto;

public record StorePasswordRequest(
        Long userId,
        String password) {
}