package com.tatazoe.ecjavajs.service;

import com.tatazoe.ecjavajs.dto.WishListDto;
import com.tatazoe.ecjavajs.exception.CustomException;
import com.tatazoe.ecjavajs.model.User;
import com.tatazoe.ecjavajs.model.WishList;
import com.tatazoe.ecjavajs.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class WishListService {
    @Autowired
    WishListRepository wishListRepository;
    @Autowired
    UserService userService;

    public WishList readWishList(String name) {
        WishList wishlist = wishListRepository.findByName(name);
        return wishlist;
    }

    public void  addWishList(WishListDto wishListDto) throws CustomException {
        User user = userService.readUser(wishListDto.getUserId());
        if (Objects.isNull(user)) {
            throw new CustomException("user Id is invalid");
        }
        WishList wishlist = new WishList(wishListDto.getName(),user);
        wishListRepository.save(wishlist);
    }

}
