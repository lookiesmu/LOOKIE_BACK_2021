package service;

import org.springframework.stereotype.Service;
import vo.UserVO;

@Service
public interface UserService {
    public void save(UserVO userVO);
    public boolean checkEmailDuplication(String email);
    //public boolean checkPassword(String email,String password);
    public UserVO findByEmail(String email);
}
