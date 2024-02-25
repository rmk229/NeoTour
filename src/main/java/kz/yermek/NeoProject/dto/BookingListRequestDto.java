package kz.yermek.NeoProject.dto;

public record BookingListRequestDto(Long bookingId, Long tourId, String imageUrl, String title) {
}
