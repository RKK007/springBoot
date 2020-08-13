package com.sfac.java_spring_boot.models.test.service.impl;

import com.sfac.java_spring_boot.models.common.vo.Result;
import com.sfac.java_spring_boot.models.test.entity.Card;
import com.sfac.java_spring_boot.models.test.repository.CardRepository;
import com.sfac.java_spring_boot.models.test.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Description CardServiceImpl
 * @Author HymanHu
 * @Date 2020/8/12 15:16
 */
@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private CardRepository cardRepository;

    @Override
    @Transactional
    public Result<Card> insertCard(Card card) {
        cardRepository.saveAndFlush(card);
        return new Result<Card>(
                Result.ResultStatus.SUCCESS.status,
                "Insert success.", card);
    }
}
