package com.ecomarket.WebApp.Controllers;

import com.ecomarket.WebApp.Service.CartService;
import com.ecomarket.WebApp.Service.GoodsService;
import com.ecomarket.WebApp.Service.UserService;
import com.ecomarket.WebApp.models.Goods;
import com.ecomarket.WebApp.models.User;
import com.ecomarket.WebApp.models.Cart;
import com.ecomarket.WebApp.repository.CartRepository;
import com.ecomarket.WebApp.repository.GoodsRepository;
import com.ecomarket.WebApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MainController {

    @ModelAttribute
    public void interceptor(Authentication authentication, Model model) {
        if (authentication == null) {
            model.addAttribute("authorized", false);
            model.addAttribute("isAdmin", false);
        } else {
            if (!authentication.getAuthorities().stream().map(d -> d.getAuthority()).toList().contains("USER")) {
                model.addAttribute("isAdmin", true);
            } else {
                model.addAttribute("isAdmin", false);
            }
            model.addAttribute("authorized", true);
        }
    }

    @Autowired
    public MainController(GoodsService goodsService) {
    }

    @Autowired
    private UserService userService;


    @Autowired
    private GoodsRepository goodsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }


    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        return "signup";
    }

    @GetMapping("/registration")
    public String registrationPage() {
        return "signup";
    }

    @PostMapping("/registration")
    public String doRegistration(@RequestParam("username") String username, @RequestParam("password") String password,
                                 @RequestParam("confirmPassword") String confirmPassword) {
        ResponseEntity response = userService.registrate(username, password, confirmPassword);
        if (response.getStatusCodeValue() == 400) return "signup";
        return "login";
    }


    @GetMapping("/goods")
    public String findAll(Model model) {
        List<Goods> goods = goodsRepository.findAll();
        model.addAttribute("goods", goods);
        return "goods";
    }


    @GetMapping("/goods/add")
    public String goodsAdd(Goods goods) {
        return "goods-create";
    }

    @PostMapping("/goods/add")
    public String createGoods(@RequestParam String name, @RequestParam String type, @RequestParam String description, @RequestParam Integer price, Model model) {
        Goods goods = new Goods(name, type, description, price);
        goodsRepository.save(goods);
        return "redirect:/goods";
    }

    @GetMapping("/goods/{id}/edit")
    public String goodsEdit(@PathVariable(value = "id") long id, Model model) {
        if (!goodsRepository.existsById(id)) {
            return "redirect:/goods";
        }
        Optional<Goods> goods = goodsRepository.findById(id);
        ArrayList<Goods> res = new ArrayList<>();
        goods.ifPresent(res::add);
        model.addAttribute("goods", res);
        return "goods-edit";
    }

    @PostMapping("/goods/{id}/edit")
    public String createGoodsUpdate(@PathVariable(value = "id") long id, @RequestParam String name, @RequestParam String type, @RequestParam String description, @RequestParam String price, Model model) {
        Goods goods = goodsRepository.findById(id).orElseThrow();
        goods.setName(name);
        goods.setType(type);
        goods.setDescription(description);
        goods.setPrice(Double.valueOf(price));
        goodsRepository.save(goods);
        return "redirect:/goods";
    }

    @PostMapping("/goods/{id}/delete")
    public String createGoodsDelete(@PathVariable(value = "id") long id, Model model) {
        Goods goods = goodsRepository.findById(id).orElseThrow();
        goodsRepository.delete(goods);
        return "redirect:/goods";
    }


    @GetMapping("/goods/doily")
    public String goodsDoily(Model model) {
        List<Goods> goods = goodsRepository.findAll();
        model.addAttribute("goods", goods);
        return "goods-doily";
    }

    @GetMapping("/goods/fiber")
    public String goodsFiber(Model model) {
        List<Goods> goods = goodsRepository.findAll();
        model.addAttribute("goods", goods);
        return "goods-fiber";
    }

    @GetMapping("/goods/soap")
    public String goodsSoap(Model model) {
        List<Goods> goods = goodsRepository.findAll();
        model.addAttribute("goods", goods);
        return "goods-soap";
    }

    @Autowired
    private CartRepository cartRepository;

    @RequestMapping("/cart")
    public String cart(Model model) {
        Iterable<Cart> carts = cartRepository.findAll();
        model.addAttribute("carts", carts);
        return "cart";
    }

    @Autowired
    private CartService cartService;

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable(name = "id") Long id_goods) {
        cartService.addToCart(id_goods);
        return "redirect:/goods";
    }

    @PostMapping({"/deleteCartItem/{cartId}"})
    public String deleteCartItem(@PathVariable(name = "cartId") Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElseThrow();
        cartService.deleteCartItemLong(cartId);
        return "redirect:/cart";
    }
}