package kz.yermek.NeoProject.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.yermek.NeoProject.dto.CreateReviewDto;
import kz.yermek.NeoProject.dto.ReviewDto;
import kz.yermek.NeoProject.exceptions.NotFoundException;
import kz.yermek.NeoProject.models.Review;
import kz.yermek.NeoProject.repositories.ReviewRepository;
import kz.yermek.NeoProject.repositories.TourRepository;
import kz.yermek.NeoProject.services.ImageService;
import kz.yermek.NeoProject.services.ReviewService;
import kz.yermek.NeoProject.util.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final TourRepository tourRepository;
    private final ReviewRepository reviewRepository;
    private final ImageService imageService;

    @Override
    public void addReview(CreateReviewDto json, MultipartFile image) {
        Review review = ReviewMapper.fromDto(json);
        review.setTour(tourRepository.findById(json.tourId()).orElseThrow());
        review.setImageProfile(imageService.processImage(image));

        reviewRepository.save(review);
    }

    @Override
    public Page<ReviewDto> getReviewsByTourId(Long tourId, int page, int size) {
        if (!tourRepository.existsById(tourId)) {
            throw new NotFoundException("Booking not found");
        }
        return reviewRepository.findAllByTourId(tourId, PageRequest.of(page, size)).map(ReviewMapper::toDto);
    }
}
