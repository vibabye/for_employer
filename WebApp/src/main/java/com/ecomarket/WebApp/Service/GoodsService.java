package com.ecomarket.WebApp.Service;

import com.ecomarket.WebApp.models.Goods;
import com.ecomarket.WebApp.repository.GoodsRepository;
import com.ecomarket.WebApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {

    @Autowired
    private static GoodsRepository goodsRepository;

    public GoodsService(UserRepository userRepository) {
        this.goodsRepository = goodsRepository;
    }

    public Goods findById(Long id) {
        return goodsRepository.getOne(id);
    }

    public Iterable<Goods> findAll() {
        return goodsRepository.findAll();
    }

    public static Goods saveGoods(Goods goods) {
        return goodsRepository.save(goods);
    }

    public void deleteById(Long id) {
        goodsRepository.deleteById(id);
    }

}
