package kz.yermek.NeoProject.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.yermek.NeoProject.dto.BookingRequestDto;
import kz.yermek.NeoProject.dto.BookingResponseDto;
import kz.yermek.NeoProject.models.BookingRequest;
import kz.yermek.NeoProject.services.BookingRequestService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/booking")
@CrossOrigin("*")
@Tag(name = "Review controller")
public class BookingRequestController {

    private final BookingRequestService bookingRequestService;

    @GetMapping
    public ResponseEntity<List<BookingResponseDto>> getBookings() {
        return ResponseEntity.ok(bookingRequestService.getBookings());
    }

    @PostMapping("/addBooking")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Add new booking request",
            description = "Add new booking request to database",
            tags = {"booking", "post"},
            responses = {
                    @ApiResponse(responseCode = "201", description = "Booking added successfully",
                            content = @Content),
                    @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
            }
    )
    public void addBooking(@RequestBody @Valid BookingRequestDto dto) {
        bookingRequestService.addBookingRequest(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponseDto> getBooking(@PathVariable Long id) {
        return ResponseEntity.ok(bookingRequestService.getBooking(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BookingRequestDto> deleteBooking(@PathVariable Long id) {
        bookingRequestService.deleteBookingRequest(id);
        return (ResponseEntity<BookingRequestDto>) ResponseEntity.ok();

    }
}
