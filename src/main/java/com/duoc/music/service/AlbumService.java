package com.duoc.music.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.duoc.music.model.Album;
import com.duoc.music.repository.AlbumRepository;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public List<Album> listar(String artist) {
        if (artist == null || artist.isBlank()) {
            return albumRepository.findAll();
        }
        return albumRepository.findByArtistContainingIgnoreCase(artist);
    }

    public Optional<Album> buscarPorId(Long id) {
        return albumRepository.findById(id);
    }

    public Album crear(Album datos) {
        datos.setIdAlbum(null);
        return albumRepository.save(datos);
    }

    public Optional<Album> actualizar(Long id, Album datos) {
        return albumRepository.findById(id).map(actual -> {
            actual.setArtist(datos.getArtist());
            actual.setTitle(datos.getTitle());
            actual.setYear(datos.getYear());
            actual.setGenre(datos.getGenre());
            actual.setPrice(datos.getPrice());

            if (datos.getCoverArt() != null) {
                actual.setCoverArt(datos.getCoverArt().isBlank() ? null : datos.getCoverArt());
            }

            return albumRepository.save(actual);
        });
    }

    public boolean eliminar(Long id) {
        if (!albumRepository.existsById(id)) {
            return false;
        }
        albumRepository.deleteById(id);
        return true;
    }
}
