package Project.Projectspring.Photo.Service;

import Project.Projectspring.Photo.Controller.PhotoController;
import Project.Projectspring.Photo.DAO.PhotoDAO;
import Project.Projectspring.Photo.VO.CreatePhotoCategoryVO;
import Project.Projectspring.Photo.VO.CreatePhotoVO;
import Project.Projectspring.Photo.VO.FindPhotosVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhotoService implements PhotoServicein{

    private final PhotoDAO photoDAO;

    @Override
    public void createPhoto(CreatePhotoVO createPhotoVO) throws Exception {
        photoDAO.createPhoto(createPhotoVO);
    }

    @Override
    public List<FindPhotosVO> Photos(String group_code) throws Exception {
        return photoDAO.Photos(group_code);
    }

    @Override
    public void createPhotoCategory(CreatePhotoCategoryVO createPhotoCategoryVO) throws Exception {
        photoDAO.createPhotoCategory(createPhotoCategoryVO);
    }

    @Override
    public String checkPhotoCategory(PhotoController.CheckPhotoCateVO checkPhotoCateVO) throws Exception {
        return photoDAO.checkPhotoCategory(checkPhotoCateVO);
    }
}
