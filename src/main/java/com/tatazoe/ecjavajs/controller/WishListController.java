package com.tatazoe.ecjavajs.controller;

import com.tatazoe.ecjavajs.config.ApiResponse;
import com.tatazoe.ecjavajs.dto.WishListDto;
import com.tatazoe.ecjavajs.exception.CustomException;
import com.tatazoe.ecjavajs.service.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping("/wishlist")
public class WishListController {


    @Autowired
    private WishListService wishListService;

    @PostMapping("")
    public ResponseEntity<ApiResponse> createWishList(@RequestBody WishListDto wishListDto) throws CustomException {
        if(Objects.nonNull(wishListService.readWishList(wishListDto.getName()))) {
            return new ResponseEntity<>(new ApiResponse(false, "The name has already in use"), HttpStatus.BAD_REQUEST);
        }

        wishListService.addWishList(wishListDto);
        return new ResponseEntity<>(new ApiResponse(true, "Wish list is created"), HttpStatus.OK);

    }
}
