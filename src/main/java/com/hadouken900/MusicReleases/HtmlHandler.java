package com.hadouken900.MusicReleases;

import com.hadouken900.MusicReleases.entities.Album;
import com.hadouken900.MusicReleases.services.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class HtmlHandler {



    private static final String PATTERN_FOR_PARSING = "<span class=\"clock\"> On (.*?)<.*?src=\"(.*?)\".*?<b>(.*?)<.*?<b>(.*?)<.*?Released: <b>(.*?)<.*?Style: (.*?)<";
    private static final List<Album> ALBUM_LIST = new ArrayList<>();
    private final String urlAddress;

    public HtmlHandler(String urlAddress) {
        this.urlAddress = urlAddress;
    }

    public void init() {
        if (!ALBUM_LIST.isEmpty()) ALBUM_LIST.clear();
        String html = getHtmlPageByURL(urlAddress);
        parseHtmlIntoAlbumObjects(html);
    }

    public List<Album> getAlbumList() {
        return ALBUM_LIST;
    }

    private String getHtmlPageByURL(String urlString) {
        URL url = null;
        HttpURLConnection urlConnection = null;
        StringBuilder sb = new StringBuilder();

        try {
            url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.addRequestProperty("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)");
            InputStream stream = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader bfreader = new BufferedReader(reader);
            String s = bfreader.readLine();
            while (s != null) {
                sb.append(s);
                s = bfreader.readLine();
            }
            return sb.toString();

        } catch (MalformedURLException e) {
            System.out.println("problems with url");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("problems with reading data");
            e.printStackTrace();
        } finally {
            if (urlConnection!=null) {
                urlConnection.disconnect();
            }
        }
        return null;
    }

    private void parseHtmlIntoAlbumObjects(String html) {
        Pattern pattern = Pattern.compile(PATTERN_FOR_PARSING);
        Matcher matcher = pattern.matcher(html);

        System.out.println("----------albums-------------------");
        Long i = 1L;
        while (matcher.find()) {
            String date = matcher.group(1);
            String img = matcher.group(2);
            String artist = matcher.group(3);
            String albumName = matcher.group(4);
            String year = matcher.group(5);
            String genre = checkForValidSyntax(matcher.group(6));
            //System.out.println("-----------id = " + id + "-------------------------");
            Album album = new Album();
            album.setImg(img);
            album.setAlbumName(albumName);
            album.setArtist(artist);
            album.setYear(year);
            album.setDate(date);
            album.setGenre(genre);

            album.setId(i);
            i++;
            System.out.println(album);
            ALBUM_LIST.add(album);
        }
    }

    private String checkForValidSyntax(String genre) {
        if (genre.contains("&#038;")){
            genre =  genre.replace("&#038;", "&");
        }
        return genre;
    }
}
