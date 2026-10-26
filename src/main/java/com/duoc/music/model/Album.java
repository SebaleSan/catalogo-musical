package com.duoc.music.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "album")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_album")
    private Long idAlbum;

    @Column(nullable = false, length = 120)
    private String artist;

    @Column(nullable = false, length = 150)
    private String title;

    private Integer year;

    @Column(nullable = false, length = 60)
    private String genre;

    @Column(nullable = false)
    private Integer price;

    @Column(length = 255)
    private String coverArt;
}
