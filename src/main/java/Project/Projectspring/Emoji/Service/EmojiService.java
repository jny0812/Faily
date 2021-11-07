package Project.Projectspring.Emoji.Service;

import Project.Projectspring.Emoji.DAO.EmojiDAO;
import Project.Projectspring.Emoji.VO.CreateEmojiVO;
import Project.Projectspring.Emoji.VO.GetEmojiVO;
import Project.Projectspring.Emoji.VO.MakeEmojiVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmojiService implements EmojiServicein{

    private final EmojiDAO emojiDAO;

    @Override
    public void createEmoji(CreateEmojiVO createEmojiVO) throws Exception {
        emojiDAO.createEmoji(createEmojiVO);
    }

    @Override
    public List<GetEmojiVO> getEmoji(int group_id) throws Exception {
        return emojiDAO.getEmoji(group_id);
    }

    @Override
    public void makeEmoji(MakeEmojiVO makeEmojiVO) throws Exception {
        emojiDAO.makeEmoji(makeEmojiVO);
    }

    @Override
    public void deleteEmoji(int emoji_id) throws Exception {
        emojiDAO.deleteEmoji(emoji_id);
    }

    @Override
    public void updateUserBondingByEmoji(int user_id) throws Exception {
        emojiDAO.updateUserBondingByEmoji(user_id);
    }
}
