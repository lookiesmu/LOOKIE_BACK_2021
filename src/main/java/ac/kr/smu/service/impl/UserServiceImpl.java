package ac.kr.smu.service.impl;

import ac.kr.smu.mapper.UserMapper;
import ac.kr.smu.service.UserService;
import ac.kr.smu.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Override
    public void save(UserVO userVO) {
        userMapper.save(userVO);
    }

    @Override
    public boolean checkEmailDuplication(String email) {
        return userMapper.checkEmailDuplication(email)==0;
    }
}