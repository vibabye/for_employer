package com.ecomarket.WebApp.Service;

import com.ecomarket.WebApp.models.User;
import com.ecomarket.WebApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id_user) {
        return userRepository.getOne(id_user);
    }

    public Iterable<User> findaLL() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteById(Long id_user) {
        userRepository.deleteById(id_user);
    }

    public User findByUsername(String username) {
        return userRepository.findByLogin(username);
    }

    public ResponseEntity registrate(String username, String password, String confirmPassword) {
        if (existsByUsername(username)) return new ResponseEntity("Такой пользователь уже существует!", HttpStatus.BAD_REQUEST);
        if (!password.equals(confirmPassword)) return new ResponseEntity("Пароли не совпадают", HttpStatus.BAD_REQUEST);
        User user = new User(username, password, "USER");
        userRepository.save(user);

        return new ResponseEntity("Пользователь зарегистрирован", HttpStatus.OK);
    }

    private boolean existsByUsername(String username) {
        return userRepository.existsByLogin(username);
    }
}
