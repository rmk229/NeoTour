package kz.yermek.NeoProject.util;


import kz.yermek.NeoProject.dto.CreateReviewDto;
import kz.yermek.NeoProject.dto.ReviewDto;
import kz.yermek.NeoProject.models.Review;

import java.time.LocalDate;

public class ReviewMapper {

    public static ReviewDto toDto(Review review) {
        return new ReviewDto(
                review.getAuthorNickname(),
                review.getImageProfile().getImageUrl(),
                review.getReviewDate(),
                review.getText()
        );
    }

    public static Review fromDto(CreateReviewDto dto) {
        Review review = new Review();
        review.setAuthorNickname(dto.author());
        review.setReviewDate(LocalDate.now());
        review.setText(dto.text());

        return review;
    }
}
