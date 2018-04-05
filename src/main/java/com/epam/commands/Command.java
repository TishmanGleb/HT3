package com.epam.commands;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Command {

    private Document document;

    //open link by "url" in time by "time"
    public boolean open(String url, int time) {
        try {
            Connection.Response response = Jsoup.connect(url).timeout(0).execute();

            if (200 == response.statusCode())
                this.document = response.parse();

            return true;
        } catch (Exception e) {
            System.out.println("Can't open URL [" + url + "].");
            return false;
        }

    }

    //return true if on page present link by href
    public boolean checkLinkPresentByHref(String href) {
        if (("".equals(href)) || (null == href) || (null == document))
            return false;

        Elements link = document.select("a[href]");
        for (Element i : link) {
            if (href.equals(i.attr("href")))
                return true;
        }

        return false;
    }

    //return true if on page present link by link name
    public boolean checkLinkPresentByName(String linkName) {
        if (("".equals(linkName)) || (null == linkName) || (null == document))
            return false;

        Elements link = document.select("a[href]");
        for (Element i : link)
            if (linkName.equals(i.text()))
                return true;

        return false;
    }

    //return true if on page present title by "text"
    public boolean checkPageTitle(String text) {
        if (("".equals(text)) || (null == text) || (null == document))
            return false;

        return document.title().equals(text);
    }

    //return true if any element on page contains "text"
    public boolean checkPageContains(String text) {
        if (("".equals(text)) || (null == text) || (null == document))
            return false;

        return document.getAllElements().toString().contains(text);
    }

}
