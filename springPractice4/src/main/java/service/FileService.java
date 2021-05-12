package service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    public void saveAll(int postId, List<MultipartFile> files);
}
