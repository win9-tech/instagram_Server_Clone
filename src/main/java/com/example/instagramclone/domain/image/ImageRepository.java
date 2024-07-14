package com.example.instagramclone.domain.image;

import com.example.instagramclone.domain.image.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}