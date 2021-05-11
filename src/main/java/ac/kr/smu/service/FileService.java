package ac.kr.smu.service;

import org.springframework.core.io.FileSystemResource;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    public void saveAll(int postId, List<MultipartFile> files);
    public FileSystemResource getFileSystemResource(int id);
}