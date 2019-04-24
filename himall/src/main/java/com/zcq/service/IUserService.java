package com.zcq.service;

import com.zcq.common.ServerResponse;
import com.zcq.pojo.User;

/**
 * @Author: zcq
 * @Date: 2019/4/24 13:34
 */
public interface IUserService {
    ServerResponse<User> login(String username,String password);

    ServerResponse<String> register(User user);

    ServerResponse<String> checkValid(String str,String type);

    ServerResponse<String> selectQuestion(String username);

    ServerResponse<String> checkAnswer(String username,String question,String answer);
}
