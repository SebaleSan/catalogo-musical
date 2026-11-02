package com.duoc.music.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duoc.music.model.Album;
import com.duoc.music.dto.AlbumRequest;
import com.duoc.music.repository.AlbumRepository;
import com.duoc.music.error.ResourceNotFoundException;

@Service
public class AlbumService {
    private static final Logger log = LoggerFactory.getLogger(AlbumService.class);

    

    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Transactional(readOnly = true)
    public List<Album> listar() {
        return albumRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Album buscarPorId(Long id) {
        return albumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Album no encontrado con id: " + id));
    }

    @Transactional(readOnly = true)
    public List<Album> buscarPorArtista(String artista) {
        return albumRepository.findByArtistContainingIgnoreCase(artista);
    }

    @Transactional
    public Album crear(AlbumRequest datos) {

        Album nuevoAlbum = new Album(null,
                datos.artist(),
                datos.title(),
                datos.year(),
                datos.genre(),
                datos.price(),
                datos.stock(),
                datos.coverArt());
                Album albumCreado = albumRepository.save(nuevoAlbum);
        log.info("Album creado: {}", albumCreado.getIdAlbum());
        return albumCreado;
    }

    @Transactional 
    public Album actualizar(Long id, AlbumRequest datos) {
        Album albumExistente = albumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Album no encontrado con id: " + id));

        albumExistente.setArtist(datos.artist());
        albumExistente.setTitle(datos.title());
        albumExistente.setYear(datos.year());
        albumExistente.setGenre(datos.genre());
        albumExistente.setPrice(datos.price());
        albumExistente.setStock(datos.stock());
        albumExistente.setCoverArt(datos.coverArt());

        Album albumActualizado = albumRepository.save(albumExistente);
        log.info("Album actualizado: {}", albumActualizado.getIdAlbum());
        return albumActualizado;
    }

    @Transactional
    public void eliminar(Long id){
        Album album = buscarPorId(id);
        albumRepository.delete(album);
        log.info("Album eliminado: {}", album.getIdAlbum());

    }





    
}
