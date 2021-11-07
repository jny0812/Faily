package Project.Projectspring.Photo.Service;

import Project.Projectspring.Photo.Controller.PhotoController;
import Project.Projectspring.Photo.DAO.PhotoDAO;
import Project.Projectspring.Photo.VO.*;
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

    @Override
    public int checkPhotoId(PhotoController.PhotoBookmarkNeedVO photoBookmarkNeedVO) throws Exception {
        return photoDAO.checkPhotoId(photoBookmarkNeedVO);
    }

    @Override
    public void setBookmark(PhotoBookmarkVO photoBookmarkVO) throws Exception {
        photoDAO.setBookmark(photoBookmarkVO);
    }

    @Override
    public Integer checkBookmarked(PhotoController.CheckBookmarkedVO checkBookmarkedVO) throws Exception {
        return photoDAO.checkBookmarked(checkBookmarkedVO);
    }

    @Override
    public void addPhotoInCategory(AddPhotoVO addPhotoVO) throws Exception {
        photoDAO.addPhotoInCategory(addPhotoVO);
    }

    @Override
    public int getPhotoId(PhotoController.PhotoBookmarkNeedVO photoBookmarkNeedVO) throws Exception {
        return photoDAO.getPhotoId(photoBookmarkNeedVO);
    }

    @Override
    public Integer checkPhotoAdded(AddPhotoVO addPhotoVO) throws Exception {
        return photoDAO.checkPhotoAdded(addPhotoVO);
    }
}
