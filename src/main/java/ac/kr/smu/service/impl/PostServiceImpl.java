package ac.kr.smu.service.impl;

import ac.kr.smu.mapper.PostMapper;
import ac.kr.smu.service.FileService;
import ac.kr.smu.service.PostService;
import ac.kr.smu.vo.PostVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class PostServiceImpl implements PostService {
    @Autowired
    private FileService fileService;

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
    @Transactional
    public void delete(int id) {
        fileService.deleteByPostId(id);
        
        postMapper.delete(id);
    }
}
