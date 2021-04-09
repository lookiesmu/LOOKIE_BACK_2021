package ac.kr.smu.mapper;

import ac.kr.smu.service.PostService;
import ac.kr.smu.vo.PostVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PostMapper {
    @Select("select * from post")
    public List<PostVO> test();
    public void save(PostVO postVO);
    public List<PostVO> findAll();
    public PostVO findById(int id);
    public PostVO update(PostVO postVO);
    public void delete(int id);
}
