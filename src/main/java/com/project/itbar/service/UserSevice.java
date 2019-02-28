package com.project.itbar.service;

import com.project.itbar.domain.User;
import com.project.itbar.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.UUID;

@Service
public class UserSevice implements UserDetailsService {
    @Value("${server.uri}")
    private String serverURI;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private MailSender mailSender;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public boolean addUser(User user) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            return false;
        }

        user.setActive(true);
        user.setActivationCode(UUID.randomUUID().toString());
        userRepo.save(user);

        if(!StringUtils.isEmpty(user.getEmail())){
            String message = String.format(
                    "Добрый день, %s! \n "+
                            "Добро пожаловать на сайт ItBar! Для окончания регистрации, пожалуйста пройди по ссылке: %s/activate/%s",
                    user.getUsername(), serverURI, user.getActivationCode());

            mailSender.sendMail(user.getEmail(), "Activation mail", message);
        }

        return true;
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);
        System.out.println(user);

        if(user == null){
            return false;
        }

        user.setActivationCode(null);
        userRepo.save(user);

        return true;
    }
}
