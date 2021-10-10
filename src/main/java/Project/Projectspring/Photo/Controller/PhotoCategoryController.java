//package Project.Projectspring.Photo.Controller;
//
//import Project.Projectspring.Join.Controller.JoinController;
//import Project.Projectspring.Photo.Service.PhotoCategoryService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.HashMap;
//
//@RestController
//@Slf4j
//@RequiredArgsConstructor
//public class PhotoCategoryController {
//
//    private final JoinController joinController;
//    private final PhotoCategoryService photoCategoryService;
//
//    //갤러리 카테고리 추가
//    @RequestMapping(value = "/add/PhotoCategory", method = RequestMethod.POST)
//    @ResponseBody
//    public HashMap<String, Object> createPhotoCategory() throws Exception {
//
//        String a = joinController.remakeJwtToken();
//    }
//
//    }
