package Project.Projectspring.Photo.Service;

import Project.Projectspring.Photo.Controller.PhotoController;
import Project.Projectspring.Photo.VO.CreatePhotoCategoryVO;
import Project.Projectspring.Photo.VO.CreatePhotoVO;
import Project.Projectspring.Photo.VO.FindPhotosVO;

import java.util.List;

public interface PhotoServicein {

    void createPhoto(CreatePhotoVO createPhotoVO) throws Exception;

    List<FindPhotosVO> Photos(String group_code) throws Exception;

    void createPhotoCategory(CreatePhotoCategoryVO createPhotoCategoryVO) throws Exception;

    String checkPhotoCategory(PhotoController.CheckPhotoCateVO checkPhotoCateVO) throws Exception;
}
