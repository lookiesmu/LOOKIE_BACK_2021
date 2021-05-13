package service;

import lombok.extern.slf4j.Slf4j;
import mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vo.PostVO;

import java.util.List;

@Slf4j
@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private FileService fileService;

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
    @Transactional
    public void delete(int id) {
        fileService.deleteByPostId(id);
        postMapper.delete(id);
    }
}
