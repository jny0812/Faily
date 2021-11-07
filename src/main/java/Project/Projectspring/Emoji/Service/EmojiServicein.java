package Project.Projectspring.Emoji.Service;

import Project.Projectspring.Emoji.VO.CreateEmojiVO;
import Project.Projectspring.Emoji.VO.GetEmojiVO;
import Project.Projectspring.Emoji.VO.MakeEmojiVO;

import java.util.List;

public interface EmojiServicein {

    void createEmoji(CreateEmojiVO createEmojiVO) throws Exception;

    List<GetEmojiVO> getEmoji(int group_id) throws Exception;

    void makeEmoji(MakeEmojiVO makeEmojiVO) throws Exception;

    void deleteEmoji(int emoji_id) throws Exception;

    void updateUserBondingByEmoji(int user_id) throws  Exception;
}
