package ac.kr.smu.service;

import ac.kr.smu.vo.PostVO;

import java.util.List;

public interface PostService {
    public void save(PostVO postVO);
    public List<PostVO> findAll();
}
