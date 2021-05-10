package service;

import lombok.RequiredArgsConstructor;
import mapper.UserMapper;
import org.springframework.stereotype.Service;
import vo.UserVO;

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

    @Override
    public boolean checkPassword(UserVO userVO) {
        return userVO.getPassword().equals(userMapper.findByEmail(userVO.getEmail()).getPassword());
    }

    @Override
    public UserVO findByEmail(String email) {
        return userMapper.findByEmail(email);
    }
}