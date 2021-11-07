package Project.Projectspring.Photo.DAO;

import Project.Projectspring.Photo.Controller.PhotoController;
import Project.Projectspring.Photo.VO.*;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PhotoDAO implements PhotoDAOin{


    private static final String NAMESPACE = "Project.Projectspring.Photo.PhotoMapper";

    private final SqlSession sqlSession;

    @Autowired
    public PhotoDAO(SqlSession sqlSession) {this.sqlSession = sqlSession;}

    @Override
    public void createPhoto(CreatePhotoVO createPhotoVO) throws Exception {
        sqlSession.insert(NAMESPACE+".createPhoto", createPhotoVO);
    }

    @Override
    public List<FindPhotosVO> Photos(String group_code) throws Exception {
        return sqlSession.selectList(NAMESPACE+".Photos",group_code);
    }

    @Override
    public void createPhotoCategory(CreatePhotoCategoryVO createPhotoCategoryVO) throws Exception {
        sqlSession.insert(NAMESPACE+".createPhotoCategory",createPhotoCategoryVO);
    }

    @Override
    public String checkPhotoCategory(PhotoController.CheckPhotoCateVO checkPhotoCateVO) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".checkPhotoCategory",checkPhotoCateVO);
    }

    @Override
    public int checkPhotoId(PhotoController.PhotoBookmarkNeedVO photoBookmarkNeedVO) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".checkPhotoId", photoBookmarkNeedVO);
    }

    @Override
    public void setBookmark(PhotoBookmarkVO photoBookmarkVO) throws Exception {
        sqlSession.insert(NAMESPACE+".setBookmark", photoBookmarkVO);
    }

    @Override
    public Integer checkBookmarked(PhotoController.CheckBookmarkedVO checkBookmarkedVO) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".checkBookmarked", checkBookmarkedVO);
    }

    @Override
    public void addPhotoInCategory(AddPhotoVO addPhotoVO) throws Exception {
        sqlSession.update(NAMESPACE+".addPhotoInCategory", addPhotoVO);
    }

    @Override
    public int getPhotoId(PhotoController.PhotoBookmarkNeedVO photoBookmarkNeedVO) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".getPhotoId",photoBookmarkNeedVO);
    }

    @Override
    public Integer checkPhotoAdded(AddPhotoVO addPhotoVO) throws Exception {
        return sqlSession.selectOne(NAMESPACE+".checkPhotoAdded",addPhotoVO);
    }
}
