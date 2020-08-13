package com.sfac.java_spring_boot.models.test.service;


import com.sfac.java_spring_boot.models.common.vo.Result;
import com.sfac.java_spring_boot.models.test.entity.Card;

/**
 * @Description CardService
 * @Author HymanHu
 * @Date 2020/8/12 15:15
 */
public interface CardService {

    Result<Card> insertCard(Card card);
}
