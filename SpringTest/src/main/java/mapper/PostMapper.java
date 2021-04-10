package mapper;

import org.apache.ibatis.annotations.Select;
import vo.PostVO;

import java.util.List;

public interface PostMapper {
    public void save(PostVO postVO);
    public List<PostVO> findAll();
    public PostVO findById(int id);
    public PostVO update(PostVO postVO);
    public void delete(int id);
}