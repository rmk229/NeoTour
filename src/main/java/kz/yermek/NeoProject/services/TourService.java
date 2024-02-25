package kz.yermek.NeoProject.services;

import kz.yermek.NeoProject.dto.TourDto;
import kz.yermek.NeoProject.dto.TourDtoFromList;
import kz.yermek.NeoProject.models.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface TourService {
    void addTour(String json, List<MultipartFile> images);
    TourDto getTourById(Long id);
    void incrementViewCount(Long id);
    Page<TourDtoFromList> getTours(Map<String, String> params);
    Page<TourDtoFromList> getPopularTours(Pageable pageable);
    Page<TourDtoFromList> getSpecialTours(Pageable pageable);
    Page<TourDtoFromList> getMostVisitedTours(Pageable pageable);

    Page<TourDtoFromList> getToursByContinent(String continent, Pageable pageable, int monthMask);

    Page<TourDtoFromList> getRecommendedTours(Integer seasonMask, int page);
    void updateTour(Tour tour);
    void deleteTour(Long id);
}
