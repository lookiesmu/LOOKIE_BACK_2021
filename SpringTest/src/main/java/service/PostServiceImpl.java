package service;

import mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import vo.PostVO;

import java.util.List;

@Repository
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostMapper postMapper;

    public void save(PostVO postVO){
        postMapper.save(postVO);
    }

    @Override
    public List<PostVO> findAll() {
        return postMapper.findAll();
    }

    @Override
    public PostVO findById(int id) {
        return postMapper.findById(id);
    }

    @Override
    public PostVO update(PostVO postVO) {
        return postMapper.update(postVO);
    }

    @Override
    public void delete(int id) {
        postMapper.delete(id);
    }
}