package com.tatazoe.ecjavajs.repository;

import com.tatazoe.ecjavajs.model.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Integer> {
    WishList findByName(String name);
}
