package com.alllexe.phonebookapp.repository;

import com.alllexe.phonebookapp.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({
        "classpath:spring/spring-db.xml"})
class UserRepositoryTest {

    @Autowired
    protected UserRepository repository;

    @Test
    void whenAdd2UsersThanShouldGetSize2() {
        User user1 = new User();
        user1.setUsername("Alex");
        repository.save(user1);
        User user2 = new User();
        user2.setUsername("Bob");
        repository.save(user2);

        assertEquals(2, repository.findAll().size());
        Assertions.assertThat(repository.findAll())
                .contains(user1, user2);

        repository.delete(user1);
        repository.delete(user2);
    }

    @Test
    void whenWas2UsersAddDeleteOneThanShouldGetSize1() {
        User user1 = new User();
        user1.setUsername("Alex");
        repository.save(user1);
        User user2 = new User();
        user2.setUsername("Bob");
        repository.save(user2);
        assertEquals(2, repository.findAll().size());
        repository.delete(user1);

        assertEquals(1, repository.findAll().size());
        Assertions.assertThat(repository.findAll())
                .contains(user2);

        repository.delete(user2);
    }

    @Test
    void whenFindByUsernameShoudFounded() {
        User user1 = new User();
        user1.setUsername("Alex");
        repository.save(user1);
        User user2 = new User();
        user2.setUsername("Bob");
        repository.save(user2);

        assertEquals(user1, repository.findByUsername(user1.getUsername()).orElse(null));

        repository.delete(user1);
        repository.delete(user2);

    }
}