package com.duoc.music.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.duoc.music.service.AlbumService;
import jakarta.validation.Valid;
import com.duoc.music.dto.AlbumResponse;
import com.duoc.music.dto.AlbumRequest;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping ("/api/albums")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping
    public List<AlbumResponse> listar() {
        return albumService.listar().stream()
                .map(AlbumResponse::from)
                .toList();
    }

    @GetMapping("/{id}")
    public AlbumResponse buscarPorId(@PathVariable("id") Long id) {
        return AlbumResponse.from(albumService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<AlbumResponse> crear(@Valid @RequestBody AlbumRequest datosAlbum) {
        return ResponseEntity.status(HttpStatus.CREATED).body(AlbumResponse.from(albumService.crear(datosAlbum)));
    }


    @PutMapping("/{id}")
    public AlbumResponse actualizar(@PathVariable ("id") Long id,
                                            @Valid @RequestBody AlbumRequest datosAlbum) {
                                                return AlbumResponse.from(albumService.actualizar(id, datosAlbum));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") Long id) {
        albumService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    
    
    
    
    
}
