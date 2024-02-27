package kz.yermek.NeoProject.util;

import kz.yermek.NeoProject.dto.CreateTourDto;
import kz.yermek.NeoProject.dto.TourDto;
import kz.yermek.NeoProject.dto.TourListDto;
import kz.yermek.NeoProject.models.Image;
import kz.yermek.NeoProject.models.Location;
import kz.yermek.NeoProject.models.Tour;

public class TourMapper {
    public static TourDto toDto(Tour tour) {
        return new TourDto(
                tour.getId(),
                tour.getTourName(),
                tour.getLocation().getPlace(),
                tour.getLocation().getCountry(),
                tour.getDescription(),
                tour.getImages().stream().map(Image::getImageUrl).toList(),
                tour.getReviews().stream().map(ReviewMapper::toDto).limit(3).toList()
        );
    }

    public static TourListDto toTourDtoFromList(Tour tour) {
        return new TourListDto(
                tour.getId(),
                tour.getImages().get(0).getImageUrl(),
                tour.getTourName()
        );
    }

    public static Tour fromDto(CreateTourDto dto) {
        Tour tour = new Tour();

        tour.setTourName(dto.name());
        tour.setLocation(new Location(dto.place(), dto.country(), dto.continent()));
        tour.setDescription(dto.description());
        tour.setSpecial(dto.isSpecial());

        return tour;
    }
}
