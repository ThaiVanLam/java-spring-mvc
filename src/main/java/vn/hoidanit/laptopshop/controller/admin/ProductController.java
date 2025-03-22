package vn.hoidanit.laptopshop.controller.admin;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UploadService;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ProductController {

    private final ProductService productService;
    private final UploadService uploadService;

    public ProductController(ProductService productService, UploadService uploadService) {
        this.uploadService = uploadService;
        this.productService = productService;
    }

    @GetMapping("/admin/product")
    public String getDashboard(Model model) {
        List<Product> products = this.productService.getAllProducts();
        model.addAttribute("products", products);
        return "admin/product/show";
    }

    @GetMapping("/admin/product/create")
    public String getAddProduct(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    @PostMapping("/admin/product/create")
    public String postAddProduct(@Valid @ModelAttribute("newProduct") Product product,
            BindingResult productBindingResult, @RequestParam("hoidanitFile") MultipartFile file) {
        List<FieldError> errors = productBindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(">>>>" + error.getField() + " - " + error.getDefaultMessage());
        }

        // validate
        if (productBindingResult.hasErrors()) {
            return "admin/product/create";
        }
        //

        String avatar = this.uploadService.handleSaveUploadFile(file, "product");
        product.setImage(avatar);
        this.productService.handleSaveProduct(product);
        return "redirect:/admin/product";

    }

    @GetMapping("/admin/product/{id}")
    public String getProductDetail(Model model, @PathVariable long id) {
        System.out.println("check path id = " + id);
        model.addAttribute("id", id);
        Product product = this.productService.getProductById(id);
        model.addAttribute("product", product);
        // get name of image file from database
        String image = product.getImage();
        String imagePath = "/images/product/" + image;
        model.addAttribute("imagePath", imagePath);
        return "admin/product/detail";
    }

    @GetMapping("/admin/product/update/{id}")
    public String getUpdateProduct(Model model, @PathVariable long id) {
        Product currentProduct = this.productService.getProductById(id);
        String image = currentProduct.getImage();
        String imagePath = "/images/product/" + image;
        model.addAttribute("newProduct", currentProduct);
        model.addAttribute("imagePath", imagePath);
        return "admin/product/update";
    }

    @PostMapping("/admin/product/update")
    public String postUpdateUser(Model model, @Valid @ModelAttribute("newProduct") Product product,
            BindingResult productBindingResult,
            @RequestParam("hoidanitFile") MultipartFile file) {
        List<FieldError> errors = productBindingResult.getFieldErrors();
        for (FieldError error : errors) {
            System.out.println(">>>>" + error.getField() + " - " + error.getDefaultMessage());
        }
        // validate
        if (productBindingResult.hasErrors()) {
            return "admin/product/update";
        }
        //
        Product currentProduct = this.productService.getProductById(product.getId());
        if (currentProduct != null) {
            if (!file.isEmpty()) {

                String avatar = this.uploadService.handleSaveUploadFile(file, "product");
                currentProduct.setImage(avatar);
            }
            currentProduct.setName(product.getName());
            currentProduct.setPrice(product.getPrice());
            currentProduct.setDetailDesc(product.getDetailDesc());
            currentProduct.setShortDesc(product.getShortDesc());
            currentProduct.setQuantity(product.getQuantity());
            currentProduct.setFactory(product.getFactory());
            currentProduct.setTarget(product.getTarget());
            this.productService.handleSaveProduct(currentProduct);
        }
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(Model model, @PathVariable long id) {
        Product product = new Product();
        product.setId(id);
        model.addAttribute("currentProduct", product);
        return "/admin/product/delete";
    }

    @PostMapping("/admin/product/delete")
    public String postDeleteProduct(Model model, @ModelAttribute("currentProduct") Product product) {
        Product currentProduct = this.productService.getProductById(product.getId());
        if (currentProduct != null) {
            this.productService.handleDeleteProduct(currentProduct.getId());
        }
        return "redirect:/admin/product";
    }

}