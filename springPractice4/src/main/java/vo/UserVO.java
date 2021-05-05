package vo;

import lombok.Data;

@Data
public class UserVO {
    private int id;
    private String name;
    private String password;
    private String email;
}
