package ac.kr.smu.vo;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class PostVO {
    private int id;
    private String title;
    private String content;
    private Timestamp created_date = new Timestamp(new Date().getTime());
    private UserVO user;
}
