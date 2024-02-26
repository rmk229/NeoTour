package kz.yermek.NeoProject.services;

import kz.yermek.NeoProject.models.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    Image processImage(MultipartFile file);

    Image saveImage(MultipartFile file);

    String uploadImage(MultipartFile file);
}
