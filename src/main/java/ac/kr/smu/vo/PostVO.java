package ac.kr.smu.vo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class PostVO {
    private int id;
    private String title;
    private String content;
    private Timestamp created_date;
    private String name;
}