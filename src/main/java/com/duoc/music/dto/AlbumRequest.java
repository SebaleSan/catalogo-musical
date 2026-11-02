package com.duoc.music.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotBlank;

public record AlbumRequest (

    @NotBlank(message = "El artista es obligatorio")
    @Size(max = 120, message = "El artista no puede tener más de 120 caracteres") String artist,

    @NotBlank(message = "El título es obligatorio")
    @Size(max = 150, message = "El título no puede tener más de 150 caracteres") String title,

    @Min(value = 1900, message = "El año debe ser mayor o igual a 1900") Integer year,

    @Size(max = 60, message = "El género no puede tener más de 60 caracteres") String genre,

    @NotNull(message = "El precio es obligatorio")
    @Min(value = 0, message = "El precio no puede ser negativo") Long price,

    @Min(value = 0, message = "El stock no puede ser negativo") Integer stock,

    @Size(max = 255, message = "La portada no puede tener más de 255 caracteres") String coverArt


) 
{}
