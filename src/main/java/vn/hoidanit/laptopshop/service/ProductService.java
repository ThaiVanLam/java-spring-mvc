package vn.hoidanit.laptopshop.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.CartDetailRepository;
import vn.hoidanit.laptopshop.repository.CartRepository;
import vn.hoidanit.laptopshop.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;
    private final UserService userService;

    public ProductService(ProductRepository productRepository, CartRepository cartRepository,
            CartDetailRepository cartDetailRepository, UserService userService) {
        this.userService = userService;
        this.cartDetailRepository = cartDetailRepository;
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public void handleSaveProduct(Product product) {
        this.productRepository.save(product);
    }

    public Optional<Product> getProductById(long id) {
        return this.productRepository.findById(id);
    }

    public void handleDeleteProduct(long id) {
        this.productRepository.deleteById(id);
    }

    public void handleAddProductToCart(String email, long productId, HttpSession session) {
        User user = userService.getUserByEmail(email);
        if (user != null) {
            // check user đã có cart hay chưa, nếu chưa tạo mới
            Cart cart = this.cartRepository.findByUser(user);
            if (cart == null) {
                Cart otherCart = new Cart();
                otherCart.setUser(user);
                otherCart.setSum(0);

                cart = this.cartRepository.save(otherCart);
            }

            // save cart_detail
            // tìm product theo id
            Optional<Product> product = this.productRepository.findById(productId);
            if (product.isPresent()) {
                // lấy ra product
                Product realProduct = product.get();

                // kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
                CartDetail oldDetail = this.cartDetailRepository.findByCartAndProduct(cart, realProduct);
                if (oldDetail == null) {

                    // tạo cart_detail
                    CartDetail cartDetail = new CartDetail();
                    cartDetail.setCart(cart);
                    cartDetail.setProduct(realProduct);
                    cartDetail.setQuantity(1);
                    cartDetail.setPrice(realProduct.getPrice());
                    this.cartDetailRepository.save(cartDetail);

                    // update sum
                    int s = cart.getSum() + 1;
                    cart.setSum(s);
                    this.cartRepository.save(cart);
                    session.setAttribute("sum", s);
                } else {
                    // nếu đã có thì tăng số lượng lên 1
                    oldDetail.setQuantity(oldDetail.getQuantity() + 1);
                    this.cartDetailRepository.save(oldDetail);

                }
            }

        }

    }

    public List<CartDetail> getAllProductsInCart(String email) {
        User user = userService.getUserByEmail(email);
        Cart cart = this.cartRepository.findByUser(user);
        List<CartDetail> cartDetails = this.cartDetailRepository.findAllByCart(cart);

        return cartDetails;
    }

}
