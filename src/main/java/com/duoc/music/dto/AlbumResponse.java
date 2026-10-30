package com.duoc.music.dto;


import com.duoc.music.model.Album;



public record AlbumResponse(Long idAlbum, String artist, String title, Integer year, String genre, Long price, Integer stock, String coverArt) 
{

    public static AlbumResponse from(Album album) {
        return new AlbumResponse(
            album.getIdAlbum(),
            album.getArtist(),
            album.getTitle(),
            album.getYear(),
            album.getGenre(),
            album.getPrice(),
            album.getStock(),
            album.getCoverArt()
        );

    }    
    
}
