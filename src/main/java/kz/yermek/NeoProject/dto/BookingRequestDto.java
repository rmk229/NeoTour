package kz.yermek.NeoProject.dto;

import javax.validation.constraints.*;

public record BookingRequestDto(
    @NotNull(message = "Tour ID cannot be null")
    @Min(value = 1, message = "Tour ID must be greater than or equal to 1")
    Long tourId,
    @NotNull(message = "Phone number cannot be null") @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$", message = "Invalid phone number format")
    String phoneNumber,
    boolean wantSavePhone,
    @Min(value = 1, message = "People count must be greater than or equal to 1")
    @Max(value = 6, message = "People count must be less than or equal to 6")
    Integer numberOfPeople,
    @Size(max = 300, message = "Comment must be less than or equal to 300 characters")
    String comment) {
}
