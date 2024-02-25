package kz.yermek.NeoProject.dto;

import java.time.LocalDate;

public record ReviewDto(String authorNickname, String imageUrl, LocalDate date, String text) {
}
