package com.duoc.music.service;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.duoc.music.model.Album;
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
    public Album crear(Album datos) {

        Album nuevoAlbum = new Album(null,
                datos.getArtist(),
                datos.getTitle(),
                datos.getYear(),
                datos.getGenre(),
                datos.getPrice(),
                datos.getStock(),
                datos.getCoverArt());
                Album albumCreado = albumRepository.save(nuevoAlbum);
        log.info("Album creado: {}", albumCreado.getIdAlbum());
        return albumCreado;
    }

    @Transactional 
    public Album actualizar(Long id, Album datos) {
        Album albumExistente = albumRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Album no encontrado con id: " + id));

        albumExistente.setArtist(datos.getArtist());
        albumExistente.setTitle(datos.getTitle());
        albumExistente.setYear(datos.getYear());
        albumExistente.setGenre(datos.getGenre());
        albumExistente.setPrice(datos.getPrice());
        albumExistente.setStock(datos.getStock());
        albumExistente.setCoverArt(datos.getCoverArt());

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
