package kz.yermek.NeoProject.controllers;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.yermek.NeoProject.dto.ReviewDto;
import kz.yermek.NeoProject.services.ReviewService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("/api/reviews")
@CrossOrigin("*")
@Tag(name = "Review controller")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/addReview")
    @ResponseStatus(HttpStatus.CREATED)
    public void addReview(@RequestPart String dto, @RequestPart("image") MultipartFile image) {
        reviewService.addReview(dto, image);
    }

    @GetMapping("/{tourId}")
    @Operation(
            summary = "Get all reviews by tour id",
            description = "Get all reviews by tour id",
            tags = {"review", "get"},
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "404", description = "Tour not found", content = @Content)
            }
    )
    public ResponseEntity<Page<ReviewDto>> getReviewsByTourId(
            @PathVariable Long tourId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size

    ) {
        return ResponseEntity.ok(reviewService.getReviewsByTourId(tourId, page, size));

    }
}
