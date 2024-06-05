package com.ecomarket.WebApp.Service;

import com.ecomarket.WebApp.models.Cart;
import com.ecomarket.WebApp.models.Goods;
import com.ecomarket.WebApp.models.User;
import com.ecomarket.WebApp.repository.CartRepository;
import com.ecomarket.WebApp.repository.GoodsRepository;
import com.ecomarket.WebApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private UserRepository userRepository;

    public void  deleteCartItemLong(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    public Cart addToCart(Long id_goods) {

        Goods goods = goodsRepository.findById(id_goods).get();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String login = authentication.getName();
        User user = userRepository.findByLogin(login);

        if (goods != null && user != null) {
            Cart cart = new Cart(goods, user);
            return cartRepository.save(cart);
        }

        return null;
    }
}
