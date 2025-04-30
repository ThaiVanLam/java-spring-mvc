package vn.hoidanit.laptopshop.controller.client;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.ProductService;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ItemController {

    private final ProductService productService;

    public ItemController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public String getProductDetail(Model model, @PathVariable long id) {
        Product pr = productService.getProductById(id).get();
        model.addAttribute("product", pr);
        model.addAttribute("id", id);
        return "client/product/detail";
    }

    @PostMapping("/add-product-to-cart/{id}")
    public String addProductToCart(@PathVariable long id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        long productId = id;
        String email = (String) session.getAttribute("email");
        this.productService.handleAddProductToCart(email, productId, session);
        return "redirect:/";
    }

    @GetMapping("/cart")
    public String getCartPage(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        List<CartDetail> cartDetails = this.productService.getAllProductsInCart(email);
        double totalPrice = 0.0;
        for (CartDetail cartDetail : cartDetails) {
            totalPrice += cartDetail.getProduct().getPrice() * cartDetail.getQuantity();
        }
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("cartDetails", cartDetails);
        return "client/cart/show";
    }

    @PostMapping("/delete-cart-product/{id}")
    public String postDeleteCartProduct(@PathVariable long id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        // find cart detail by id
        CartDetail cartDetail = productService.getCartDetailById(id);
        // get cart by cart detail
        Cart cart = cartDetail.getCart();
        // delete cart detail by id
        this.productService.deleteCartDetailById(id);
        // update cart
        if (cart.getSum() == 1) {
            this.productService.deleteCartById(cart.getId());
            // set session sum of cart to 0
            session.setAttribute("sum", 0);
        } else {
            session.setAttribute("sum", cart.getSum() - 1);
            this.productService.updateCart(cart);
        }
        return "redirect:/cart";
    }

}
