package ac.kr.smu.controller;

import ac.kr.smu.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/{postId}/file")
@RequiredArgsConstructor
public class FileController {
    private final FileService fileService;

    @PostMapping
    public void postFile(@RequestParam("files") List<MultipartFile> files, @PathVariable("postId") int postId){
        fileService.saveAll(postId,files);
    }
}