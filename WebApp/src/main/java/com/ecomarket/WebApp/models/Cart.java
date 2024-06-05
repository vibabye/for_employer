package com.ecomarket.WebApp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Класс, представляющий корзину, хранит информацию о её id, id пользователя и id товара.
 */

/**
 * Описание аннотаций:
 * @Entity Предоставляем доступ к данным
 * @Table Указываем таблицу
 * @Data Getter & Setter
 * @AllArgsConstructor Консруктор для создания объектов
 * @NoArgsConstructor Нулевой конструктор
 * @Column Обозначение клонок
 * @Id Обозначение уникального идентификатора
 * @GeneratedValue Автоматическая генерация ключа
 * @OneToOne Связь между таблицами
 */
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @OneToOne
    private Goods goods;

    @OneToOne
    private User user;

    public Cart(Goods goods, User user) {
        this.goods = goods;
        this.user = user;
    }
}
