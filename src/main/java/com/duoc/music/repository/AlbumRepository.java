package com.duoc.music.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.duoc.music.model.Album;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    List<Album> findByArtistContainingIgnoreCase(String artist);
}
