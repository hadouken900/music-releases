package com.hadouken900.MusicReleases.controllers;

import com.hadouken900.MusicReleases.HtmlHandler;
import com.hadouken900.MusicReleases.entities.Album;
import com.hadouken900.MusicReleases.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class AlbumController {

    AlbumService albumService;

    @Autowired
    public void setAlbumService(AlbumService albumService) {
        this.albumService = albumService;
    }

    private static String url = "https://newalbumreleases.net/category/cat/";
    private static int currentPage = 0;
    private static List<Album> albums;


    @GetMapping
    public String showAlbumList(Model model) {

        albums = albumService.getAllAlbums();
        model.addAttribute("albums", albums);
        return "albums";
    }

    @GetMapping("/refresh")
    public String refreshAlbums(Model model) {

        HtmlHandler handler = new HtmlHandler(url);
        handler.init();
        albums = handler.getAlbumList();
        albumService.clearTable();
        albumService.saveAllAlbums(albums);

        model.addAttribute("albums", albums);
        return "redirect:/";
    }

    @PostMapping("/filter")
    public String filterProducts(Model model, @RequestParam String filter) {
        albums = albumService.getAlbumsByFilteredGenre(filter);
        model.addAttribute("albums", albums);
        model.addAttribute("filter", filter);
        return "albums";
    }
}
