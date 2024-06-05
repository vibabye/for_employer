package com.ecomarket.WebApp.models;


import javax.persistence.*;
import lombok.*;

/**
 * Класс, представляющий пользователя, хранит информацию о его id, логине, пароле и роли.
 */

/**
 * Описание аннотаций:
 * @Entity Предоставляем доступ к данным
 * @Table(name = "users")  Указываем название таблицы
 * @Data Getter & Setter
 * @AllArgsConstructor Консруктор для создания объектов
 * @NoArgsConstructor Нулевой конструктор
 * @Column Обозначение клонок
 * @Id Обозначение уникального идентификатора
 * @GeneratedValue Автоматическая генерация ключа
 */
@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    @Column(name = "id_user")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_user;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    public User(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(Long idUser) {
    }


}
