package com.ecomarket.WebApp.models;

import javax.persistence.*;
import lombok.*;

/**
 * Класс, представляющий товары, хранит информацию о id, имени, типе, описании и цене.
 */

/**
 * Описание аннотаций:
 * @Entity Предоставляем доступ к данным
 * @Table Указываем название таблицы
 * @Data Getter & Setter
 * @AllArgsConstructor Консруктор для создания объектов
 * @NoArgsConstructor // Нулевой конструктор
 * @Column Обозначение клонок
 * @Id Обозначение уникального идентификатора
 * @GeneratedValue Автоматическая генерация ключа
 */
@Entity
@Table(name = "goods")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Goods {

    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    public Goods(String name, String type, String description, Integer price) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.price = Double.valueOf(price);
    }

}