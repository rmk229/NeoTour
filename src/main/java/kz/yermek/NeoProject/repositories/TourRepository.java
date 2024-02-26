package kz.yermek.NeoProject.repositories;

import kz.yermek.NeoProject.models.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TourRepository extends JpaRepository<Tour, Long> {
    Page<Tour> findAllByOrderByViewCountDesc(Pageable pageable);
    Page<Tour> findAllByOrderByBookingCountDesc(Pageable pageable);
    @Query("SELECT t FROM Tour t WHERE BITAND(t.recommendedSeasons, :seasonMask) > 0")
    Page<Tour> findRecommendedTours(int seasonMask, PageRequest pageRequest);
    @Query("SELECT t FROM Tour t WHERE (t.isSpecial = true AND BITAND(t.recommendedSeasons, :seasonMask) > 0)")
    Page<Tour> findAllBySpecialTrue(int seasonMask, Pageable pageable);
    @Query("SELECT t FROM Tour t WHERE (LOWER(t.location.continent) = LOWER(:continent) AND BITAND(t.recommendedSeasons, :seasonMask) > 0)")
    Page<Tour> findAllByLocationContinent(String continent, Pageable pageable, int seasonMask);
    @Modifying
    @Query("UPDATE Tour t SET t.bookingCount = t.bookingCount + 1 WHERE t.id = :id")
    void incrementBookingCount(Long id);
    @Modifying
    @Query("UPDATE Tour t SET t.viewCount = t.viewCount + 1 WHERE t.id = :id")
    void incrementViewCount(Long id);
}
