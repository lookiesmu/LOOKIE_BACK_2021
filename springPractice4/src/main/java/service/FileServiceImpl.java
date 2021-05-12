package service;

import config.WebConfig;
import lombok.RequiredArgsConstructor;
import mapper.FileMapper;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vo.FileVO;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final FileMapper fileMapper;

    @Override
    public void saveAll(int postId, List<MultipartFile> files) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String upload_date = sdf.format(date);
        upload_date.replace("-", File.separator);
        File uploadPath = new File(WebConfig.UPLOAD_PATH,upload_date);

        // date에 맞는 폴더 없으면 새로 생성
        if(!uploadPath.exists())
            uploadPath.mkdir();

        for(MultipartFile file : files){
            String fileName = file.getOriginalFilename();
            UUID uuid = UUID.randomUUID();

            FileVO fileVO = FileVO.builder().postId(postId).name(fileName).uuid(uuid.toString())
                    .uploadPath(uploadPath.toPath().toString()).build();

            fileName = uuid.toString() + "_" + fileName;
            File uploadFile = new File(uploadPath,fileName);

            try{
                file.transferTo(uploadFile);
                fileMapper.save(fileVO);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public FileSystemResource getFileSystemResource(int id) {
        FileSystemResource resource = new FileSystemResource(fileMapper.findById(id).getPath());

        if(!resource.exists())
            return null;

        return resource;
    }

}

