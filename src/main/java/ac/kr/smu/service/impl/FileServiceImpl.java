package ac.kr.smu.service.impl;

import ac.kr.smu.config.WebConfig;
import ac.kr.smu.mapper.FileMapper;
import ac.kr.smu.service.FileService;
import ac.kr.smu.vo.FileVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final FileMapper fileMapper ;

    @Override
    public void saveAll(int postId, List<MultipartFile> files){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String upload_date = sdf.format(date);
        upload_date.replace("-", File.separator);
        File uploadPath = new File(WebConfig.UPLOAD_PATH, upload_date);

        // 현재 날짜의 폴더가 없다면 생성

        if(!uploadPath.exists())
            log.info("패스 없네");
            uploadPath.mkdir();


        log.info("패스 있니?");
        log.info(String.valueOf(!uploadPath.exists()));
        // 파일을 하나씩 저장
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
            }catch (IOException e){e.printStackTrace();}
        }
    }
}
