package kz.yermek.NeoProject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public record ReviewDto(String authorNickname,
                        String imageUrl,
                        @JsonFormat(pattern = "yyyy-MM-dd")
                        LocalDate date,
                        String text) {
}
