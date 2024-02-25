package kz.yermek.NeoProject.controllers;



import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import kz.yermek.NeoProject.dto.TourDto;
import kz.yermek.NeoProject.dto.TourDtoFromList;
import kz.yermek.NeoProject.models.Tour;
import kz.yermek.NeoProject.repositories.TourRepository;
import kz.yermek.NeoProject.services.TourService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/tours")
@CrossOrigin("*")
@Tag(name = "Tour controller")
public class TourController {

    private final TourService tourService;
    private final TourRepository tourRepository;

    @PostMapping("/addTour")
    @ResponseStatus(HttpStatus.CREATED)
    public void addTour(@RequestPart String dto, @RequestPart("images")List<MultipartFile> images) {
        tourService.addTour(dto, images);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Get tour by id",
            description = "Get tour by id",
            tags = {"tour", "get"},
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "404", description = "Tour not found", content = @Content)
            }
    )
    public ResponseEntity<TourDto> getTourById(@PathVariable Long id) {
        tourService.incrementViewCount(id);
        return ResponseEntity.ok(tourService.getTourById(id));
    }


    @GetMapping
    @Operation(
            summary = "Get tours",
            description = "Get tours based on parameter.",
            tags = {"tour", "get"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Paged list of tours"),
                    @ApiResponse(responseCode = "400", description = "Invalid parameter", content = @Content)
            },
            parameters = {
                    @Parameter(name = "param", description = "Parameter to search by",
                            examples = {
                                    @ExampleObject(name = "Popular tours", value = "popular"),
                                    @ExampleObject(name = "Featured tours", value = "featured"),
                                    @ExampleObject(name = "Most visited tours", value = "visited"),
                                    @ExampleObject(name = "Recommended tours", value = "recommended")
                            }, required = true),
                    @Parameter(name = "page", description = "Page number", example = "0"),
                    @Parameter(name = "size", description = "Page size", example = "3"),
                    @Parameter(name = "season", description = "Recommended in season...", example = "3")
            }
    )
    public ResponseEntity<Page<TourDtoFromList>> getTours(@RequestParam Map<String, String> params) {
        return ResponseEntity.ok(tourService.getTours(params));
    }

    @GetMapping("/getTours")
    public ResponseEntity<List<Tour>> getTours() {
        return ResponseEntity.ok(tourRepository.findAll());
    }

    @PutMapping("/updateTour")
    public void updateTour(@RequestBody Tour tour){
        tourService.updateTour(tour);
    }

    @DeleteMapping("/{id}")
    public void deleteTour(@PathVariable Long id) {
        tourService.deleteTour(id);
    }

}
