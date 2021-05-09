package service;

import vo.PostVO;

import java.util.List;

public interface PostService {

    public void save(PostVO postVO);
    public List<PostVO> findAll();
    public PostVO findById(int id);
    public PostVO update(PostVO postVO);
    public void delete(int id);
}