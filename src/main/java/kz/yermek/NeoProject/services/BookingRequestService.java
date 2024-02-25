package kz.yermek.NeoProject.services;

import kz.yermek.NeoProject.dto.BookingRequestDto;
import kz.yermek.NeoProject.dto.BookingResponseDto;
import kz.yermek.NeoProject.models.BookingRequest;

import java.util.List;

public interface BookingRequestService {
    void addBookingRequest(BookingRequestDto requestDto);

    BookingResponseDto getBooking(Long id);

    List<BookingRequest> getBookings();

    void deleteBookingRequest(Long id);
}
