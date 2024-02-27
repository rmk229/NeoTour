package kz.yermek.NeoProject.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kz.yermek.NeoProject.dto.CreateTourDto;
import kz.yermek.NeoProject.dto.TourDto;
import kz.yermek.NeoProject.dto.TourListDto;
import kz.yermek.NeoProject.models.Tour;
import kz.yermek.NeoProject.repositories.TourRepository;
import kz.yermek.NeoProject.services.ImageService;
import kz.yermek.NeoProject.services.TourService;
import kz.yermek.NeoProject.util.Seasons;
import kz.yermek.NeoProject.util.TourMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TourServiceImpl implements TourService {
    private final TourRepository tourRepository;
    private final ImageService imageService;
    private final ObjectMapper mapper = new ObjectMapper();


    @Override
    public void addTour(CreateTourDto json, List<MultipartFile> images) {
        Tour tour  = TourMapper.fromDto(json);
        int seasons = 0;
        if (json.seasons() != null) {
            for (int season : json.seasons()) {
                seasons |= Seasons.ALL[season - 1];
            }
        }
        tour.setRecommendedSeasons(seasons);
        for (MultipartFile image : images) {
            tour.addImage(imageService.processImage(image));
        }
        tourRepository.save(tour);
    }

//    @Override
//    public void addTour(CreateTourDto json, List<MultipartFile> file) {
//
//    }

//    @Override
//    public void addTour(String json, List<MultipartFile> file) {
//        CreateTourDto dto;
//        try {
//            dto = mapper.readValue(json, CreateTourDto.class);
//        } catch (JsonProcessingException e) {
//            throw new RuntimeException(e);
//        }
//        Tour tour = TourMapper.fromDto(dto);
//        int seasons = 0;
//        if (dto.seasons() != null) {
//            for (int month : dto.seasons()) {
//                seasons |= Seasons.ALL[month - 1];
//            }
//        }
//
//        tour.setRecommendedSeasons(seasons);
//        file.stream().map(imageService::processImage).forEach(tour::addImage);
//        tourRepository.save(tour);
//    }

    @Override
    @Cacheable(value = "tourDetailsCache", key = "#id")
    public TourDto getTourById(Long id) {
        Tour tour = tourRepository.findById(id).orElseThrow();
        return TourMapper.toDto(tour);
    }

    @Override
    @Transactional
    public void incrementViewCount(Long id) {
        tourRepository.incrementViewCount(id);
    }

    @Override
    public Page<TourListDto> getTours(String params, int season, int page, int size) {
        if (season != 0) {
            if (season < 1 || season > 4) {
                throw new IllegalArgumentException("Season should be between 1 and 4");
            }
        } else {
            season = LocalDate.now().getMonthValue();
        }
        int seasonMask = Seasons.ALL[season - 1];

        if (page < 0 || size < 1) {
            throw new IllegalArgumentException("Invalid page of size");
        }

        Pageable pageable = PageRequest.of(page, size);

        return switch (params) {
            case "popular" -> getPopularTours(pageable);
            case "featured" -> getSpecialTours(pageable);
            case "visited" -> getMostVisitedTours(pageable);
            case "recommended" -> getRecommendedTours(seasonMask, page);
            default -> getToursByContinent(params, pageable, seasonMask);
        };
    }

    @Override
    public Page<TourListDto> getPopularTours(Pageable pageable) {
        return tourRepository.findAllByOrderByViewCountDesc(pageable)
                .map(TourMapper::toTourDtoFromList);
    }

    @Override
    public Page<TourListDto> getSpecialTours(Pageable pageable) {
        return tourRepository.findAllBySpecialTrue(Seasons.getCurrentSeasonMask(), pageable)
                .map(TourMapper::toTourDtoFromList);
    }

    @Override
    public Page<TourListDto> getMostVisitedTours(Pageable pageable) {
        return tourRepository.findAllByOrderByBookingCountDesc(pageable)
                .map(TourMapper::toTourDtoFromList);
    }

    @Override
    public Page<TourListDto> getToursByContinent(String continent, Pageable pageable, int seasonMask) {
        return tourRepository.findAllByLocationContinent(continent, pageable, seasonMask)
                .map(TourMapper::toTourDtoFromList);
    }

    @Override
    public Page<TourListDto> getRecommendedTours(Integer seasonMask, int page) {
        int size = 4;
        return tourRepository.findRecommendedTours(seasonMask, PageRequest.of(page, size))
                .map(TourMapper::toTourDtoFromList);
    }
    @Override
    public List<TourDto> getAllTours() {
        List<Tour> responseDtos = tourRepository.findAll();
        return responseDtos.stream().map(TourMapper::toDto).toList();
    }

    @Override
    public void updateTour(Tour tour) {
        tourRepository.save(tour);
    }

    @Override
    public void deleteTour(Long id) {
        tourRepository.deleteById(id);
    }
}
