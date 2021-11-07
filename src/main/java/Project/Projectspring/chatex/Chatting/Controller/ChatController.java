package Project.Projectspring.chatex.Chatting.Controller;

import Project.Projectspring.Emoji.Service.EmojiService;
import Project.Projectspring.Emoji.VO.CreateEmojiVO;
import Project.Projectspring.Group.Service.GroupService;
import Project.Projectspring.Home.Controller.HomeApiController;
import Project.Projectspring.Join.Controller.JoinController;
import Project.Projectspring.Photo.Service.PhotoService;
import Project.Projectspring.Photo.VO.CreatePhotoVO;
import Project.Projectspring.Question.Service.QuestionService;
import Project.Projectspring.chatex.Chatting.ChatSender;
import Project.Projectspring.chatex.Chatting.Service.ChatService;
import Project.Projectspring.chatex.Chatting.VO.ChattingListVO;
import Project.Projectspring.chatex.Chatting.VO.MessageVO;
import Project.Projectspring.chatex.Push.Service.PushService;
import Project.Projectspring.chatex.Push.Service.RabbitService;
import Project.Projectspring.chatex.Push.VO.ChatPutVO;
import Project.Projectspring.chatex.Push.VO.ChatVO;
import Project.Projectspring.chatex.Push.VO.ReceiverListVO;
import com.google.firebase.ErrorCode;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
@RequiredArgsConstructor
@Slf4j
public class ChatController {

    private final JoinController joinController;
    private final QuestionService questionService;
    private final ChatService chatService;
    private final PushService pushService;
    private final PhotoService photoService;
    private final GroupService groupService;
    private final EmojiService emojiService;

    @PostMapping("/chat")
    public boolean chatController(final @RequestBody MessageVO messageVO) throws InterruptedException {
        sender.send(messageVO.getGroup_code(),messageVO.getContent());
        return true;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class SendChatVO {
        private String content;
//        private MultipartFile file_multi;
        private byte[] file;
        private boolean emoji;
        private boolean photo;
        private boolean text;
        private boolean calendar;
    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChatRequest {
        private String content;
        private byte [] file_byte;
        private String user_name;
    }

    private final ChatSender sender;
    private final SimpMessageSendingOperations msgTemplate;
    private final RabbitService rabbitService;


    /** 채팅 전송 **/  //form-data 타입
    @RequestMapping(value = "/chat/msg", method = RequestMethod.POST)
    @ResponseBody
//    public HashMap<String, Object> Send(@ModelAttribute SendChatVO sendChatVO) throws Exception {
     public HashMap<String, Object> Send(@RequestBody SendChatVO sendChatVO) throws Exception {


                HashMap<String, Object> result = new HashMap<>();

        String e_mail = joinController.getSubject(); //이메일 추출
        int user_id = questionService.userIdCheck(e_mail);    //receiver_id 추출
        String group_code = chatService.getGroupCode(user_id); //group_code 추출
        String sender_name = chatService.getUserName(user_id);
        int group_id = groupService.groupIdCheck(group_code);


        ChatVO chatVO = new ChatVO(sendChatVO.getContent(), 0,null,group_code,0,null, null);
        String path = "";
        try{

            byte [] file = sendChatVO.getFile();

//            if(file != null){
//                path = saveFile(sendChatVO.getFile());
////                byte[] file_byte = transferUserFile(path);
////                log.warn(String.valueOf(file_byte));
//                chatVO.setFile(file);
//            }

//            MultipartFile file_multi = sendChatVO.getFile_multi();
////            if(file_multi != null){
//                path = saveFile(file_multi);
//                byte[] file = transferUserFile(path);
//                log.warn(String.valueOf(file));
//                chatVO.setFile(file);
////            }


            msgTemplate.convertAndSend("/topic/messages." +String.valueOf(group_code), new ChatRequest(sendChatVO.getContent(),file, sender_name));
//        msgTemplate.convertAndSend("/topic/messages." + String.valueOf(chatVO.getGroup_code()) , new ChatRequest(chatVO.getContent(), chatVO.getSender_name()));
//        rabbitService.rabbitChatProducer(chatVO);


            List<ReceiverListVO> receivers =  pushService.Receivers(group_code); //receiver 리스트
//            log.warn(String.valueOf(receivers.size()));
            for(int i=0;i<receivers.size();i++) {

                int receiver_id = Integer.parseInt(String.valueOf(receivers.get(i).getUser_id()));
                log.warn(String.valueOf(receiver_id));
                String content = chatVO.getContent();

                chatVO.setSender_name(sender_name);
                log.warn(chatVO.getSender_name());

                int sender_id = user_id;
                log.warn(String.valueOf(sender_id));

                chatVO.setSender_id(sender_id);

                Date time = new Date();
                SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                Calendar cal = Calendar.getInstance();
                cal.setTime(time);
                String chatting_time = sdformat.format(cal.getTime());  //채팅 시간

                chatVO.setChatting_time(chatting_time);
                chatVO.setReceiver_id(receiver_id);


                ChatPutVO chatPutVO = new ChatPutVO(sendChatVO.getContent(), sender_id, sender_name, group_code, receiver_id,
//                        chatting_time,file,emoji_bool,photo_bool,text_bool,calendar_bool);
                        chatting_time, file, sendChatVO.isEmoji(), sendChatVO.isPhoto(), sendChatVO.isText(), sendChatVO.isCalendar());

                pushService.putChatting(chatPutVO);  //채팅 테이블에 insert

                //photo인 경우
                if(sendChatVO.isPhoto() == true) {
                    if(receiver_id==sender_id){
                    CreatePhotoVO createPhotoVO = new CreatePhotoVO(file,sender_id, sender_name,group_code,group_id,chatting_time);
                    photoService.createPhoto(createPhotoVO);  //photo 테이블에 insert
                }}

                //이모티콘인 경우
//                if(sendChatVO.isEmoji() == true) {
//                    if(receiver_id==sender_id){
//                        CreateEmojiVO createEmojiVO = new CreateEmojiVO(file,sender_id, sender_name,group_id,chatting_time);
//                        emojiService.createEmoji(createEmojiVO);  //emoji 테이블에 insert
//                    }}


            }
                log.warn("insert 성공");

            rabbitService.rabbitChatProducer(chatVO);
            log.warn("rabbitservice complete");

            Date time = new Date();
            SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            Calendar cal = Calendar.getInstance();
            cal.setTime(time);
            String chatting_time = sdformat.format(cal.getTime());  //채팅 시간

        result.put("isSuccess", true);
        result.put("code",200);
        result.put("message","채팅 전송 완료");
        result.put("content", sendChatVO.getContent());
        result.put("file",file);
        result.put("sender", sender_name);
        result.put("group_code", group_code);
        result.put("chatting_time", chatting_time);
        result.put("emoji  ",sendChatVO.isEmoji());
        result.put("photo  ",sendChatVO.isPhoto());
        result.put("text",sendChatVO.isText());
        result.put("calendar",sendChatVO.isCalendar());
        }
        catch (Exception e) {
           log.warn(String.valueOf(e));
            result.put("isSuccess", false);
            result.put("code",302);
            result.put("message","유효하지 않은 사용자입니다.");}

        return result;
    }



    /** 채팅 확인 (채팅 목록 불러오기) **/

//    @Getter
//    @Setter
//    @NoArgsConstructor
//    @AllArgsConstructor
//    public static class UnreadListVO {
//        private String group_code;
//        private String content;
//        private String sender_name;
//        private int user_id;
//        private String file;
//    }


    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChatChatVO {
        private int unread;
        private String sender_name;
        private String content;
//        private String file;
        private byte[] file;
//        private String group_code;
        private String chatting_time;

        private boolean emoji;
        private boolean photo;
        private boolean text;
        private boolean calendar;

    }
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UnreadListVO {
        private String group_code;
        private String content;
        private byte [] chatting_file;
        private String sender_name;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class responseAnswer {
        boolean IsSuccess;
        int code;
        String message;
        List<ChatChatVO> result;

    }

    @RequestMapping(value = "/chat/list", method = RequestMethod.GET)
    @ResponseBody
    public Object ChattingList() throws Exception{

        HashMap<String, Object> result = new HashMap<>();

        try {

            String e_mail = joinController.getSubject(); //이메일 추출
            int user_id = questionService.userIdCheck(e_mail);    //receiver_id 추출
            String group_code = chatService.getGroupCode(user_id); //group_code 추출


           responseAnswer response = new responseAnswer(true, 200,
                   "채팅 목록을 불러왔습니다", null);

            log.warn(group_code);

            List<ChattingListVO> chattingListVO = chatService.ChatList(group_code);  //채팅 내용 불러오기

            List<ChatChatVO> list = new ArrayList<>();

            log.warn(String.valueOf(chattingListVO.size()));

            for (int i =0;i<chattingListVO.size();i++) {

                chatService.updateIsRead(user_id);   //'읽음' 처리

                UnreadListVO unreadListVO = new UnreadListVO(group_code,chattingListVO.get(i).getContent(),
                       chattingListVO.get(i).getChatting_file(),chattingListVO.get(i).getSender_name() );
                log.warn("content"+chattingListVO.get(i).getContent());

                int unread = chatService.numberOfUnread(unreadListVO);  //읽지 않은 인원 수
                log.warn(String.valueOf(unread));

            ChatChatVO chatChatVO = new ChatChatVO(unread,chattingListVO.get(i).getSender_name(),chattingListVO.get(i).getContent(),
                    chattingListVO.get(i).getChatting_file(),chattingListVO.get(i).getChatting_time(),
                    chattingListVO.get(i).isEmoji(),chattingListVO.get(i).isPhoto(),chattingListVO.get(i).isCalendar(),
                    chattingListVO.get(i).isText());

                list.add(chatChatVO);

                response.setResult(list);
            }
            return response;

        } catch (Exception e) {

            log.warn(String.valueOf(e));
            result.put("isSuccess", false);
            result.put("code", 301);
            result.put("message", "유효하지 않은 사용자입니다.");
            return result;
        }


    }


    /** 파일 저장을 위한 md5 hash 생성 */
    public String saveFileNameToHash(String filename) throws NoSuchAlgorithmException {
        /**
         * 이름이 똑같으면 hash 값이 똑같이 나온다.
         * 확장자를 빼내고 파일 이름만 해싱한다.
         * 이 때 파일 이름을 filename + datetime으로 한다.
         */
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(filename.getBytes(StandardCharsets.UTF_8));

        byte[] mdHash = md.digest();
        StringBuilder hashBuilder = new StringBuilder();
        for(byte b : mdHash) {
            String hexString = String.format("%02x", b);
            hashBuilder.append(hexString);
        }
        return hashBuilder.toString();
    }

    /** multipartfile을 저장하는 로직 - 파일 하나 */
    public String saveFile(MultipartFile file) throws NoSuchAlgorithmException, IOException {

        String savePath = "/home/ubuntu/family/var";
//        String savePath = "C:/Users/user/Desktop/family";
        if (!new File(savePath).exists()) {
            new File(savePath).mkdir();
        }
        String originName = file.getOriginalFilename();
        String saveName = saveFileNameToHash(originName + new Date());
        savePath += "/" + saveName;
        file.transferTo(new File(savePath));

        return savePath;
    }

    public byte[] transferUserFile(String path) throws IOException{
        try{
            FileInputStream in = new FileInputStream(new File(path));
            return IOUtils.toByteArray(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        }
    }





}