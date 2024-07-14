package com.example.instagramclone.domain.image.service;

import com.example.instagramclone.aws.service.AwsS3Service;
import com.example.instagramclone.domain.image.ImageRepository;
import com.example.instagramclone.domain.image.entity.Image;
import com.example.instagramclone.domain.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final AwsS3Service awsS3Service;

    @Override
    public void uploadAndCreateImages(List<MultipartFile> imageList, Post post) {
        List<String> imageUrls = imageList
                .stream()
                .map(awsS3Service::uploadFile)
                .toList();

        for (String url : imageUrls) {
            Image image = new Image(url, post);
            imageRepository.save(image);
        }
    }
}
