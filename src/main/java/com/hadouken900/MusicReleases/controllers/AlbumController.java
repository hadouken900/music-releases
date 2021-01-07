package com.hadouken900.MusicReleases.controllers;

import com.hadouken900.MusicReleases.entities.Album;
import com.hadouken900.MusicReleases.entities.User;
import com.hadouken900.MusicReleases.services.AlbumService;
import com.hadouken900.MusicReleases.services.HtmlHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    private static final String URL = "https://newalbumreleases.net/category/cat/";

    @GetMapping
    public String showAlbumList(Model model,@AuthenticationPrincipal User user) {
        if (user != null) {
            model.addAttribute("user_id", user.getId());
        }
        List<Album> albums = albumService.getAllAlbums();
        model.addAttribute("albums", albums);
        return "albums";
    }

    @GetMapping("/refresh")
    @Secured("ROLE_ADMIN")
    public String refreshAlbums(Model model, @AuthenticationPrincipal User user) {

        if (user != null) {
            model.addAttribute("user_id", user.getId());
        }

        HtmlHandler handler = new HtmlHandler(URL);
        handler.init();
        List<Album> albums = handler.getAlbumList();
        albumService.clearTable();
        albumService.saveAllAlbums(albums);

        model.addAttribute("albums", albums);
        return "redirect:/";
    }

    @PostMapping("/filter")
    public String filterProducts(Model model, @RequestParam String filter,  @AuthenticationPrincipal User user) {

        if (user != null) {
            model.addAttribute("user_id", user.getId());
        }
        List<Album> albums = albumService.getAlbumsByFilteredGenre(filter);
        model.addAttribute("albums", albums);
        model.addAttribute("filter", filter);
        return "albums";
    }


    @GetMapping("/clear")
    @Secured("ROLE_ADMIN")
    public String clearTable() {
        albumService.clearTable();
        return "albums";
    }

    @GetMapping("/ajaxxx")
    @Secured("ROLE_ADMIN")
    public ModelAndView showListWithAjax() {
        ModelAndView modelAndView = new ModelAndView("albums :: resultsList");
        HtmlHandler handler = new HtmlHandler(URL);
        handler.init();
        List<Album> albums = handler.getAlbumList();
        albumService.clearTable();
        albumService.saveAllAlbums(albums);
        modelAndView.addObject("albums",albums);
        modelAndView.setViewName("albums");
        return modelAndView;

    }

    @GetMapping("/ajaxxxfilt")
    public ModelAndView filterWithAjax(Model model, @RequestParam String filter,  @AuthenticationPrincipal User user) {

        ModelAndView modelAndView = new ModelAndView("albums :: resultsList");
        if (user != null) {
            model.addAttribute("user_id", user.getId());

        }
        List<Album> albums = albumService.getAlbumsByFilteredGenre(filter);
        modelAndView.addObject("albums", albums);
        return modelAndView;
    }
}
