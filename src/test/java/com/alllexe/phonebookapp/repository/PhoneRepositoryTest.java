package com.alllexe.phonebookapp.repository;

import com.alllexe.phonebookapp.model.Phone;
import com.alllexe.phonebookapp.model.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@ContextConfiguration({
        "classpath:spring/spring-db.xml"})
public class PhoneRepositoryTest {

    @Autowired
    protected PhoneRepository repository;

    @Test
    public void whenAdd2PhonesShould2Phones() {
        User user1 = new User();
        user1.setUsername("Alex");
        Phone phone1 = new Phone();
        phone1.setPhoneNumber("4554");
        repository.save(phone1);
        Phone phone2 = new Phone();
        phone2.setPhoneNumber("4556");
        repository.save(phone2);

        assertEquals(2, repository.findAll().size());
        Assertions.assertThat(repository.findAll())
                .contains(phone1, phone2);

        repository.delete(phone1);
        repository.delete(phone2);

    }

    @Test
    public void findByPhoneNumberAndUser() {
    }

    @Test
    public void findByPhoneNumber() {
        System.out.println(repository.findByPhoneNumber("444"));
    }

    @Test
    public void deletePhoneByPhoneNumber() {
    }
}