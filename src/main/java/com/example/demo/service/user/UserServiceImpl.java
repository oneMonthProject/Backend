package com.example.demo.service.user;

import com.example.demo.dto.common.ResponseDto;
import com.example.demo.global.exception.customexception.UserCustomException;
import com.example.demo.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public ResponseDto<?> checkEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            throw UserCustomException.ALREADY_EMAIL;
        }

        return ResponseDto.success("사용가능한 이메일입니다.");
    }

    @Override
    public ResponseDto<?> checkNickname(String nickname) {
        if (userRepository.existsByNickname(nickname)) {
            throw UserCustomException.ALREADY_NICKNAME;
        }

        return ResponseDto.success("사용가능한 닉네임입니다.");
    }
}
