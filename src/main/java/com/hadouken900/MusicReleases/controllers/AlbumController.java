package com.hadouken900.MusicReleases.controllers;

import com.hadouken900.MusicReleases.HtmlHandler;
import com.hadouken900.MusicReleases.entities.Album;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class AlbumController {

    private static String url = "https://newalbumreleases.net/category/cat/";
    private static int currentPage = 0;
    private static List<Album> albums;


    @GetMapping
    public String showAlbumList(Model model) {

        HtmlHandler handler = new HtmlHandler(url);
        handler.init();
        albums = handler.getAlbumList();

        model.addAttribute("albums", albums);
        return "albums";
    }
}
