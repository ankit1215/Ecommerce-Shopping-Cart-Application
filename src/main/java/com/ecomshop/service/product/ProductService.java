package com.ecomshop.service.product;

import com.ecomshop.dto.ImageDto;
import com.ecomshop.dto.ProductDto;
import com.ecomshop.exception.ProductNotFoundException;
import com.ecomshop.model.Category;
import com.ecomshop.model.Image;
import com.ecomshop.model.Product;
import com.ecomshop.repository.CategoryRepository;
import com.ecomshop.repository.ImageRepository;
import com.ecomshop.repository.ProductRepository;
import com.ecomshop.request.AddProductRequest;
import com.ecomshop.request.UpdateProductRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class ProductService implements IProductService{

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ImageRepository imageRepository;


    @Override
    public Product addProduct(AddProductRequest request) {

        //check if the category is found in the DB
        //if yes, set it as the new product category
        //if no, the save it as a new category
        //the set as the new product category

        Category category = Optional.ofNullable(categoryRepository.findByName(request.getCategory().getName()))
                .orElseGet(()->{
                    Category newCategory = new Category(request.getCategory().getName());
                    return categoryRepository.save(newCategory);
                });
        request.setCategory(category);
        return productRepository.save(createProduct(request, category));
    }

    private Product createProduct(AddProductRequest request, Category category){
        return new Product(
                request.getName(),
                request.getBrand(),
                request.getPrice(),
                request.getInventory(),
                request.getDescription(),
                category
        );
    }
    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new ProductNotFoundException("Product Not Found!!"));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.findById(id)
                .ifPresentOrElse(productRepository::delete,
                        () ->{throw new ProductNotFoundException("Product Not Found!!");});
    }

    @Override
    public Product updateProduct(UpdateProductRequest request, Long productId) {
        return productRepository.findById(productId)
                .map(existingProduct -> updateExistingProduct(existingProduct, request))
                .map(productRepository :: save)
                .orElseThrow(()-> new ProductNotFoundException("Product Not Found !"));
    }

    private Product updateExistingProduct(Product existingProduct, UpdateProductRequest request){
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setInventory(request.getInventory());
        existingProduct.setDescription(request.getDescription());

        Category category = categoryRepository.findByName(request.getCategory().getName());
        existingProduct.setCategory(category);
        return existingProduct;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryNameAndBrand(category,brand);
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }

    @Override
    public List<ProductDto> getConvertedProducts(List<Product> products) {
        return products.stream().map(this::convertToDto).toList();
    }
//
//    @Override
//    public ProductDto convertToDto(Product product){
//        ProductDto productDto = modelMapper.map(product, ProductDto.class);
//        List<Image> images = imageRepository.findByProductId(product.getId());
//        List<ImageDto> imageDtos = images.stream()
//                .map(image -> modelMapper.map(image, ImageDto.class))
//                .toList();
//        productDto.setImages(imageDtos);
//        return productDto;
//    }

    @Override
    public ProductDto convertToDto(Product product) {
        ProductDto productDto = modelMapper.map(product, ProductDto.class);

        // Fetch images from DB
        List<Image> images = imageRepository.findByProductId(product.getId());

        // Manually map to ImageDto
        List<ImageDto> imageDtos = images.stream()
                .map(image -> {
                    ImageDto imageDto = new ImageDto();
                    imageDto.setId(image.getId());
                    imageDto.setFileName(image.getFileName());
                    imageDto.setDownloadUrl("/image/download/" + image.getId());
                    return imageDto;
                })
                .toList();

        productDto.setImages(imageDtos);
        return productDto;
    }
}
