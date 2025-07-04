package com.ecomshop.service.image;

import com.ecomshop.model.Image;
import org.springframework.web.multipart.MultipartFile;

public interface ImplImageService {

    Image getImageById(Long id);

    void deleteImageById(Long id);

    Image saveImage(MultipartFile file, Long productId);

    void updateImage(MultipartFile file, Long productId);
}
