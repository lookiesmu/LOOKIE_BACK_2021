package ac.kr.smu.mapper;

import ac.kr.smu.vo.PostVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PostMapper {
    @Select("select * from post")
    public List<PostVO> test();
}
