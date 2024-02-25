package kz.yermek.NeoProject.util;

import kz.yermek.NeoProject.dto.BookingListRequestDto;
import kz.yermek.NeoProject.dto.BookingResponseDto;
import kz.yermek.NeoProject.models.BookingRequest;

public class BookingMapper {

    public static BookingResponseDto toResponse(BookingRequest booking) {
        return new BookingResponseDto(
                booking.getId(),
                booking.getTour().getId(),
                booking.getTour().getTourName(),
                booking.getBookingRequestDate(),
                booking.getPhoneNumber(),
                booking.getComments()
        );
    }

    public static BookingListRequestDto toListRequest(BookingRequest booking) {
        return new BookingListRequestDto(
                booking.getId(),
                booking.getTour().getId(),
                booking.getTour().getImages().get(0).getImageUrl(),
                booking.getTour().getTourName()
        );
    }
}
