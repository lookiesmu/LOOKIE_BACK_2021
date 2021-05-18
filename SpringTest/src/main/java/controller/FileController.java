package controller;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import service.FileService;

import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping(value = "/{postId}/file")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @GetMapping(value = "/{fileId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<FileSystemResource> getFile(@PathVariable("fileId") int fileId,
                                                      @RequestHeader("User-Agent") String userAgent){
        FileSystemResource resource = fileService.getFileSystemResource(fileId);

        if(resource==null)
            return ResponseEntity.notFound().build();

        String fileName = resource.getFilename();

        fileName = fileName.substring(fileName.indexOf("_")+1);
        HttpHeaders headers = new HttpHeaders();

        try{
            if(userAgent.contains("Chrome"))
                fileName = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
            else
                fileName = URLEncoder.encode(fileName,"UTF-8");

            if(userAgent.contains("Safari"))
                headers.add("Content-Disposition", "attachment; filename*=utf-8''"+fileName);
            else
                headers.add("Content-Disposition","attachment; filename="+fileName);


        }catch (Exception e){e.printStackTrace();}

        return new ResponseEntity(resource,headers, HttpStatus.OK);
    }
    @PostMapping
    public void postFile(@RequestParam("files") List<MultipartFile> files, @PathVariable("postId") int postId){
        fileService.saveAll(postId,files);
    }
}