package com.hadouken900.MusicReleases.controllers;

import com.hadouken900.MusicReleases.HtmlHandler;
import com.hadouken900.MusicReleases.entities.Album;
import com.hadouken900.MusicReleases.entities.User;
import com.hadouken900.MusicReleases.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    public String showAlbumList(Model model,@AuthenticationPrincipal User user) {
        if (user != null) {
            model.addAttribute("user_id", user.getId());
        }
        albums = albumService.getAllAlbums();
        model.addAttribute("albums", albums);
        return "albums";
    }

    @GetMapping("/refresh")
    public String refreshAlbums(Model model, @AuthenticationPrincipal User user) {

        if (user != null) {
            model.addAttribute("user_id", user.getId());
        }

        HtmlHandler handler = new HtmlHandler(url);
        handler.init();
        albums = handler.getAlbumList();
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
        albums = albumService.getAlbumsByFilteredGenre(filter);
        model.addAttribute("albums", albums);
        model.addAttribute("filter", filter);
        return "albums";
    }

    @GetMapping("/ajaxrefresh")
    @ResponseBody
    public String refreshWithAjax() {


        return "What the fuck is wrong with you?!";
    }

    @GetMapping("/clear")
    public String clearTable() {
        albumService.clearTable();
        return "albums";
    }

    @GetMapping("/ajaxxx")
    public ModelAndView showListWithAjax() {
        ModelAndView modelAndView = new ModelAndView("albums :: resultsList");
        HtmlHandler handler = new HtmlHandler(url);
        handler.init();
        albums = handler.getAlbumList();
        albumService.clearTable();
        albumService.saveAllAlbums(albums);
        modelAndView.addObject("albums",albums);
        modelAndView.setViewName("albums");
        return modelAndView;

    }

    @GetMapping("/ajaxxxfilt")
    public ModelAndView ajaxfilterProducts(Model model, @RequestParam String filter,  @AuthenticationPrincipal User user) {

        ModelAndView modelAndView = new ModelAndView("albums :: resultsList");
        if (user != null) {
            model.addAttribute("user_id", user.getId());

        }
        albums = albumService.getAlbumsByFilteredGenre(filter);
        modelAndView.addObject("albums", albums);
        //model.addAttribute("filter", filter);
        return modelAndView;
    }
}
