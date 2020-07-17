package com.alllexe.phonebookapp.repository;

import com.alllexe.phonebookapp.model.Phone;
import com.alllexe.phonebookapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface PhoneRepository extends JpaRepository<Phone, Integer> {

    Optional<Phone> findByPhoneNumberAndUser(String integer, User user);

    List<Phone> findByPhoneNumber(String phoneNumber);

    @Transactional
    @Modifying
    @Query("DELETE FROM Phone p WHERE p.phoneNumber = :phoneNumber")
    void deletePhoneByPhoneNumber(@Param("phoneNumber") String phoneNumber);
}
