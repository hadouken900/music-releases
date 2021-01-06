package com.hadouken900.MusicReleases.controllers;

import com.hadouken900.MusicReleases.entities.Album;
import com.hadouken900.MusicReleases.entities.Role;
import com.hadouken900.MusicReleases.entities.User;
import com.hadouken900.MusicReleases.entities.UserMusic;
import com.hadouken900.MusicReleases.services.AlbumService;
import com.hadouken900.MusicReleases.services.UserMusicService;
import com.hadouken900.MusicReleases.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private UserMusicService userMusicService;


    @GetMapping("/registration")
    public String userRegistrationForm() {
        return "registration";
    }


    @PostMapping("/registration")
    public String registerUser(
            Model model,
            @RequestParam String username,
            @RequestParam String password
    ){
        User user = new User(username,password);


        if (!userService.saveUser(user)){
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "registration";
        }

        return "redirect:/";
    }

    @GetMapping("/{username}/add/{id}")
    public String addToFavorite(@PathVariable Long id,
                                @PathVariable String username){

        //TODO save to userMusic table
        Album album = albumService.getAlbumById(id);
        User user = userService.findUserByUsername(username);
        UserMusic um = userMusicService.findByImageAndUserId(album.getImg(), user.getId());
        if (um == null) {
            UserMusic userMusic = userMusicService.createUserMusicFromAlbumAndUserId(album, user.getId());
            userMusicService.saveAlbum(userMusic);
        }
        return "redirect:/";
    }

    @GetMapping("/{username}/delete/{id}")
    public String deleteFromFavorite(@PathVariable String username,
                                     @PathVariable Long id){

        //TODO save to userMusic table
        User user = userService.findUserByUsername(username);
        userMusicService.deleteAlbum(id);
        return "redirect:/profile/" + user.getUsername();
    }

    @PostMapping("/profile/filter")
    public String filter(Model model, @RequestParam String filter) {

        List<UserMusic> albums = userMusicService.getAlbumsByFilteredGenre(filter);
        model.addAttribute("albums", albums);
        model.addAttribute("filter", filter);
        return "favAlbums";

    }


    @GetMapping("/profile/{name}")
    public String showFavorites(Model model,@PathVariable String name) {
        User user = userService.findUserByUsername(name);
        List<UserMusic> userMusic = userMusicService.findAllById(user.getId());
        model.addAttribute("albums", userMusic);
        return "favAlbums";

    }
}
