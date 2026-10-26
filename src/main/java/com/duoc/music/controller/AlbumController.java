package com.duoc.music.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.music.model.Album;
import com.duoc.music.service.AlbumService;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping ("/api/albums")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    public List<Album> listar(@RequestParam(required = false) String artist) {
        return albumService.listar(artist);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Album> buscarPorId(@PathVariable("id") Long id) {
        Optional<Album> album = albumService.buscarPorId(id);
        return album.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Album> crear(@RequestBody Album datosAlbum) {
        Album nuevoAlbum = albumService.crear(datosAlbum);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoAlbum);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Album> actualizar(
            @PathVariable("id") Long id,
            @RequestBody Album datosAlbum) {
        Optional<Album> resultado = albumService.actualizar(id, datosAlbum);

        return resultado.map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) {
        boolean eliminado = albumService.eliminar(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    
    
    
}
