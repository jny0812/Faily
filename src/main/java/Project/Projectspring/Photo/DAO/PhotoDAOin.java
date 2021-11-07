package Project.Projectspring.Photo.DAO;

import Project.Projectspring.Photo.Controller.PhotoController;
import Project.Projectspring.Photo.VO.*;

import java.util.List;

public interface PhotoDAOin {

    void createPhoto(CreatePhotoVO createPhotoVO) throws Exception;

    List<FindPhotosVO> Photos(String group_code) throws Exception;

    void createPhotoCategory(CreatePhotoCategoryVO createPhotoCategoryVO) throws Exception;

    String checkPhotoCategory(PhotoController.CheckPhotoCateVO checkPhotoCateVO) throws Exception;

    int checkPhotoId(PhotoController.PhotoBookmarkNeedVO photoBookmarkNeedVO) throws Exception;

    void setBookmark(PhotoBookmarkVO photoBookmarkVO) throws Exception;

    Integer checkBookmarked(PhotoController.CheckBookmarkedVO checkBookmarkedVO) throws Exception;

    void addPhotoInCategory(AddPhotoVO addPhotoVO) throws Exception;

    int getPhotoId(PhotoController.PhotoBookmarkNeedVO photoBookmarkNeedVO) throws Exception;

    Integer checkPhotoAdded(AddPhotoVO addPhotoVO) throws Exception;
}
