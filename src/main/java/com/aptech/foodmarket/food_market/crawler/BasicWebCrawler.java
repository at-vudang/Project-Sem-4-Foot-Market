package com.aptech.foodmarket.food_market.crawler;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.aptech.foodmarket.food_market.model.*;
import com.aptech.foodmarket.food_market.repository.CategoryRepository;
import com.aptech.foodmarket.food_market.repository.ImageRepository;
import com.aptech.foodmarket.food_market.service.CategoryService;
import com.aptech.foodmarket.food_market.service.ItemService;
import com.aptech.foodmarket.food_market.utils.FileUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationHome;
import org.springframework.stereotype.Service;

@Service
public class BasicWebCrawler {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    private HashSet<String> links;
    private String linksCategory;
    public BasicWebCrawler() {
        links = new HashSet<String>();
    }
    public void getItems(String url, List<Category> categories) {
        List<Item> items = new ArrayList<>();
        try {
            Document document = Jsoup.connect(url).get();
            Elements itemElements = document.select(".body-list-item .item");
            for (Element el: itemElements
                 ) {
                try {
                    String urlImage = el.select(".post-thumb img").attr("data-src");
                    String name = el.select(".post-title a").attr("title");
                    Float price = Float.parseFloat(el.select(".item-content .adr-coupon").attr("data-price"));
                    String urlDetail = el.select(".post-title a").attr("href");
                    document = Jsoup.connect("https://adayroi.com/" + urlDetail).get();
                    Element elDetail = document.select("#tab_content_product_introduction").first();
                    String description = elDetail.html();
                    Item item = new Item();
                    item.setActive(true);
                    item.setPrice(price);
                    item.setAvatar(FileUtil.getImages(urlImage,"/home/vudang/Documents/Sem4/Images/Upload/"));
                    item.setName(name);
                    item.setDescription(description);
                    item.setStatus(true);
                    item.setQuantity(100);
                    item.setCategories(categories);
                    Unit unit = new Unit();
                    unit.setId(1);
                    item.setUnit(unit);
                    Supplier supplier = new Supplier();
                    supplier.setId(1);
                    item.setSupplier(supplier);
                    item = itemService.create(item);
                    System.out.println(item.getName());
                    Elements elmImageDetail = document.select("#product_gallery .thumbnails .items .item");
                    for (Element elImage: elmImageDetail
                            ) {
                        try {
                            String image = elImage.select("img").attr("src");
                            ImageItem imageItem = new ImageItem();
                            imageItem.setImage(image);
                            imageItem.setItem(item);
                            imageItem.setActive(true);
                            imageRepository.save(imageItem);
                        } catch (Exception ex) {
                            System.err.println("Error: " + ex.getMessage());
                        }
                    }
                } catch (Exception ex) {
                    System.err.println("Error: " + ex.getMessage());
                }

            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        return;
    }

    public void crawler(String URL) {
        try {
            Document document = Jsoup.connect(URL).get();
            Elements catesOnPage = document.select("#segment_navigation__mega_menu__list a");
            for (Element cate : catesOnPage) {
                    Category category = new Category();
                    category.setName(cate.select(".title").text());
                    category.setActive(true);
                    category.setLevelCategory(1);
                    category = categoryService.create(category);
                    Integer parentId;
                    try{
                        parentId = category.getId();
                    } catch (Exception ex){
                        System.err.println("For '" + URL + "': " + ex.getMessage());
                        category = categoryRepository.findFirstByName(cate.select(".title").text());
                        System.out.println(category.getId());
                        parentId = category.getId();
                    }
                    document = Jsoup.connect("https://www.adayroi.com" + cate.attr("href")).get();
                    catesOnPage = document.select(".widget-breadcrumb .active .list-unstyled li");
                    for (Element cateSub : catesOnPage) {
                        try {
                            List<Category> categories = new ArrayList<>();

                            category = new Category();
                            category.setName(cateSub.select("a").text());
                            category.setActive(true);
                            category.setParentId(parentId);
                            category.setLevelCategory(2);
                            try {
                                categories.add(categoryRepository.findOne(parentId));
                                category = categoryService.create(category);
                                categories.add(category);
                            } catch (Exception ex){
                                System.err.println("For '" + URL + "': " + ex.getMessage());
                            }
                            getItems("https://www.adayroi.com/" + cateSub.select("a").attr("href")
                                    ,categories);
                            System.out.println(category.getName());
                        } catch (Exception ex){
                            System.err.println("For '" + URL + "': " + ex.getMessage());
                        }
                    }
            }
        } catch (Exception e) {
            System.err.println("For '" + URL + "': " + e.getMessage());
        }
        return;
    }
    public void getPageLinks(String URL) {
        //4. Check if you have already crawled the URLs
        //(we are intentionally not checking for duplicate content in this example)
        if (!links.contains(URL)) {
            try {
                //4. (i) If not add it to the index
                if (links.add(URL)) {
                    System.out.println(URL);
                }

                //2. Fetch the HTML code
                Document document = Jsoup.connect(URL).get();
                //3. Parse the HTML to extract links to other URLs
                Elements linksOnPage = document.select("a[href]");

                //5. For each extracted URL... go back to Step 4.
                for (Element page : linksOnPage) {
                    getPageLinks(page.attr("abs:href"));
                }
            } catch (IOException e) {
                System.err.println("For '" + URL + "': " + e.getMessage());
            }
        }
    }
}
