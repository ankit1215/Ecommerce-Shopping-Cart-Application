package com.ecomshop.service.image;

import com.ecomshop.dto.ImageDto;
import com.ecomshop.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImplImageService {

    Image getImageById(Long id);

    void deleteImageById(Long id);

    List<ImageDto> saveImages(List<MultipartFile> files, Long productId);

    void updateImage(MultipartFile file, Long imageId);
}
