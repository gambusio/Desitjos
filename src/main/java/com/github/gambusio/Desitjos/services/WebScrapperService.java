package com.github.gambusio.Desitjos.services;

import com.github.gambusio.Desitjos.entities.Item;
import com.github.gambusio.Desitjos.entities.ItemType;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class WebScrapperService {
    private final String sUserAgent = "Mozilla/5.0 (Windows NT 10.0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36";
    private Document document = null;
    private Item item;
    public WebScrapperService() {}

    /**
     * Return true if the URL is reachable
     * @param url
     * @return
     */
    public boolean isUrlOk(String url) {
        Connection.Response response = null;
        try {
	        response = Jsoup.connect(url)
                    .userAgent(sUserAgent)
                    .timeout(100000)
                    .ignoreHttpErrors(true)
                    .execute();
        } catch (IOException ex) {
	        System.out.println("[ERROR CHECKING URL]: " + ex.getMessage());
        }
        return response.statusCode() == 200;
    }

    
    public void setHtmlDocument(String url) {

        try {
            document = Jsoup.connect(url)
                    .userAgent(sUserAgent)
                    .timeout(100000)
                    .get();
        } catch (IOException ex) {
            System.out.println("[ERROR RETRIEVING HTML]" + ex.getMessage());
        }
    }

    public void parseUrl(String sUrl) {
        String[] temp;
        item = new Item();
        if (isUrlOk(sUrl)) {
            setHtmlDocument(sUrl);
            Element title = document.getElementById("productTitle");
            Element prize = document.getElementsByClass("a-offscreen").first();
            Element description = document.getElementsByClass("ac-keyword-link").first();
            if (description == null) {
                description = document.getElementsByClass("cat-link").first();
            }

            item.setsName(title.text());
            item.setPrice(prize.text());
            if (description != null) {
                item.setsDescription(description.text());
            }
            Element picUrl = document.getElementsByClass("imgTagWrapper").first();
            if (picUrl == null) {
                picUrl = document.getElementById("img-canvas");
                temp = picUrl.html().split("<img alt=\"\" src=")[1].split(" ");
            } else {
                temp = picUrl.html().split("src=")[1].split(" ");
            }

            item.setsPicUrl(temp[0].substring(1, temp[0].length() - 1));
            item.setItemType(ItemType.ECOMMERCE);

        } else {
            System.out.println("[ERROR PARSING URL]");
        }
    }

    public Item getItem() { return item;}

}
