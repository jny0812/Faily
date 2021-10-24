//package Project.Projectspring.chatex.Firebase.Controller;
//
//import Project.Projectspring.chatex.Firebase.Service.NotificationService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
///**사용자가 로그인 후 Firebase에게 전달받은 token 값을 웹서버에게 등록한다.**/
//@RestController
//public class NotificationApiController {
//
//    private final NotificationService notificationService;
//
//    public NotificationApiController(NotificationService notificationService) {
//        this.notificationService = notificationService;
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity register(@RequestBody String token, @LoginUser UserSession userSession) {
//        notificationService.register(userSession.getId(), token);
//        return ResponseEntity.ok().build();
//    }
//
//
//    private void createReceiveNotification(s sender, User receiver) {
//        if (receiver.isLogin()) {
//            NotificationRequest notificationRequest = NotificationRequest.builder()
//                    .title("POST RECEIVED")
//                    .token(notificationService.getToken(receiver.getId()))
//                    .message(NotificationType.POST_RECEIVED.generateNotificationMessage(sender, receiver))
//                    .build();
//            notificationService.sendNotification(notificationRequest);
//        }
//    }
//
//    private void createTaggedNotification(User sender, List<User> receivers) {
//        receivers.stream()
//                .filter(User::isLogin)
//                .forEach(receiver -> {
//                    NotificationRequest notificationRequest = NotificationRequest.builder()
//                            .title("POST TAGGED")
//                            .token(notificationService.getToken(receiver.getId()))
//                            .message(NotificationType.POST_TAGGED.generateNotificationMessage(sender, receiver))
//                            .build();
//                    notificationService.sendNotification(notificationRequest);
//                });
//    }
//
//}