package com.example.products;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/")
    public String home(Locale locale, Model model) {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
        String serverTime = dateFormat.format(date);
        model.addAttribute("serverTime", serverTime);
        List<Product> productList = productService.getProducts();
        model.addAttribute("productList", productList);
        return "product/index";
    }

    @GetMapping("/product/seed")
    public String seed() {
        productService.seed();
        return "redirect:/product/";
    }

    @GetMapping("/product/add")
    public String add(Model model) {
        model.addAttribute("product", new Product());
        return "product/add";
    }

    @PostMapping("/product/add")
    public String add(@ModelAttribute Product product) {
        productService.addProduct(product);
        return "redirect:/product/";
    }


    // how to put a parameter in a query string
    // e.a. /product/details?id=3
    @GetMapping("/product/details")
    public String add(@RequestParam("id") long inputId, Model model) {
        model.addAttribute("product", productService.getProductById(inputId));
        return "/product/details";
    }

    // how to put parameter in a URL
    // e.a. /product/3/edit
    @GetMapping(value = {"/product/edit"})
    public String edit(@RequestParam("id") long productId, Model model) {
        model.addAttribute("product", productService.getProductById(productId));
        return "/product/edit";
    }

    @PostMapping(value = {"/product/edit"})
    public String edit(@ModelAttribute Product product) {
        productService.updateProduct(product);
        return "redirect:/product/";
    }

    // how to put a parameter in a query string
    // e.a. /product/remove?id=3
    @GetMapping("/product/remove")
    public String remove(@RequestParam("id") long productId) {
        productService.removeProductById(productId);
        return "redirect:/product/";
    }

    @GetMapping("/product/about")
    public String about() {
        return "/product/about";
    }
}
