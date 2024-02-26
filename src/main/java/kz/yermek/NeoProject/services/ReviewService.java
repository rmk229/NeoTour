package kz.yermek.NeoProject.services;

import kz.yermek.NeoProject.dto.CreateReviewDto;
import kz.yermek.NeoProject.dto.ReviewDto;
import kz.yermek.NeoProject.models.Review;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ReviewService {
    void addReview(CreateReviewDto json, MultipartFile image);
    Page<ReviewDto> getReviewsByTourId(Long tourId, int page, int size);

}
