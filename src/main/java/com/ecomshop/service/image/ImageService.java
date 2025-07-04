package com.ecomshop.service.image;

import com.ecomshop.exception.ResourceNotFoundException;
import com.ecomshop.model.Image;
import com.ecomshop.repository.ImageRepository;
import com.ecomshop.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class ImageService implements ImplImageService{

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    IProductService iProductService;

    @Override
    public Image getImageById(Long id) {
        return imageRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("No Image Found with id: " + id));
    }

    @Override
    public void deleteImageById(Long id) {
        imageRepository.findById(id).ifPresentOrElse(imageRepository::delete, () ->{
            throw new ResourceNotFoundException("No Image Found with id: " + id);
        });
    }

    @Override
    public Image saveImage(MultipartFile file, Long productId) {
        return null;
    }

    @Override
    public void updateImage(MultipartFile file, Long productId) {

    }
}
