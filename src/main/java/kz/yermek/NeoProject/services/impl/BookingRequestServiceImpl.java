package kz.yermek.NeoProject.services.impl;

import kz.yermek.NeoProject.dto.BookingRequestDto;
import kz.yermek.NeoProject.dto.BookingResponseDto;
import kz.yermek.NeoProject.exceptions.NotFoundException;
import kz.yermek.NeoProject.models.BookingRequest;
import kz.yermek.NeoProject.models.Tour;
import kz.yermek.NeoProject.repositories.BookingRequestRepository;
import kz.yermek.NeoProject.repositories.TourRepository;
import kz.yermek.NeoProject.services.BookingRequestService;
import kz.yermek.NeoProject.util.BookingMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingRequestServiceImpl implements BookingRequestService {

    private final TourRepository tourRepository;
    private final BookingRequestRepository bookingRequestRepository;

    @Override
    @Transactional
    public void addBookingRequest(BookingRequestDto requestDto) {
        Tour tour = tourRepository.findById(requestDto.tourId()).orElseThrow();
        BookingRequest bookingRequest = new BookingRequest();
        bookingRequest.setTour(tour);
        bookingRequest.setBookingRequestDate(LocalDate.now());
        bookingRequest.setNumberOfPeople(requestDto.numberOfPeople());
        bookingRequest.setPhoneNumber(requestDto.phoneNumber());
        bookingRequest.setComments(requestDto.comment());
        bookingRequestRepository.save(bookingRequest);
        tourRepository.incrementBookingCount(bookingRequest.getTour().getId());
        BookingMapper.toResponse(bookingRequest);
    }

    @Override
    public BookingResponseDto getBooking(Long id) {
        return BookingMapper.toResponse(bookingRequestRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Booking not found")));
    }

    @Override
    public List<BookingRequest> getBookings() {
        return bookingRequestRepository.findAll();
    }

    @Override
    public void deleteBookingRequest(Long id) {
        try {
            bookingRequestRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException("Booking not found");
        }
    }
}
