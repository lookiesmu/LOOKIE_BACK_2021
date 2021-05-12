package mapper;

import vo.FileVO;

public interface FileMapper {
    public void save(FileVO fileVO);
    public FileVO findById(int id);
}
