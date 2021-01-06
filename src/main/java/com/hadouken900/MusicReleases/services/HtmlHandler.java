package com.hadouken900.MusicReleases.services;

import com.hadouken900.MusicReleases.entities.Album;

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

/*
Класс для парсинга данных с сайта https://newalbumreleases.net
с помощью регулярного выражения. В конструктор передается урл
страницы. Первая страница находится /category/cat/ , вторая и
последующие /category/cat/page/x/ , где х - номер страницы.

*/
public class HtmlHandler {

    /*
    Регулярное выражение разбито на 6 групп:
    1 - дата
    2 - обложка
    3 - исполнитель
    4 - название альбома
    5 - год выпуска
    6 - жанр
    */
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

    //метод для получения тела хтмл страницы
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

    //парсинг с помощью Pattern и Matcher
    private void parseHtmlIntoAlbumObjects(String html) {
        Pattern pattern = Pattern.compile(PATTERN_FOR_PARSING);
        Matcher matcher = pattern.matcher(html);

        while (matcher.find()) {
            String date = matcher.group(1);
            String img = matcher.group(2);
            String artist = checkForValidSyntax(matcher.group(3));
            String albumName = checkForValidSyntax(matcher.group(4));
            String year = matcher.group(5);
            String genre = checkForValidSyntax(matcher.group(6));

            Album album = new Album(date, img, artist, albumName, year,genre);

            ALBUM_LIST.add(album);
        }
    }

    //В названиях исполнителя, альбома а также жанра, могут содержаться неподдерживаемые символы
    //Этот метод заменяет невалидные символы
    private String checkForValidSyntax(String s) {
        if (s.contains("&#038;")){
            s =  s.replace("&#038;", "&");
        }
        else if(s.contains("&#8217;")) {
            s = s.replace("&#8217;", "'");
        }
        return s;
    }
}
