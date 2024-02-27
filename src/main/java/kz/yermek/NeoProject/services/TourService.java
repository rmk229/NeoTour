package kz.yermek.NeoProject.services;

import kz.yermek.NeoProject.dto.*;
import kz.yermek.NeoProject.models.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface TourService {
    void addTour(CreateTourDto json, List<MultipartFile> file);

//    void addTour(String json, List<MultipartFile> file);

    TourDto getTourById(Long id);
    void incrementViewCount(Long id);
    Page<TourListDto> getTours(String params, int season, int page, int size);
    Page<TourListDto> getPopularTours(Pageable pageable);
    Page<TourListDto> getSpecialTours(Pageable pageable);
    Page<TourListDto> getMostVisitedTours(Pageable pageable);
    Page<TourListDto> getToursByContinent(String continent, Pageable pageable, int seasonMask);
    Page<TourListDto> getRecommendedTours(Integer seasonMask, int page);
    List<TourDto> getAllTours();
    void updateTour(Tour tour);
    void deleteTour(Long id);
}
