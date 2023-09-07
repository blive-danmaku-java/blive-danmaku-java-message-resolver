package net.dengzixu.blivedanmaku.data;


import net.dengzixu.blivedanmaku.annotation.DataResolver;
import net.dengzixu.blivedanmaku.enums.CommandEnum;
import net.dengzixu.blivedanmaku.enums.MessageEnum;
import net.dengzixu.blivedanmaku.exception.CommandErrorException;
import net.dengzixu.blivedanmaku.message.Message;
import net.dengzixu.blivedanmaku.message.content.GuardBuyContent;
import net.dengzixu.blivedanmaku.message.metadata.UserInfo;

@DataResolver(command = CommandEnum.GUARD_BUY)
public class GuardBugResolver extends AbstractDataResolver<GuardBuyContent> {
    public GuardBugResolver(String rawMessageString) {
        super(rawMessageString);
        if (!super.messageNode.get("cmd").textValue().equals("GUARD_BUY")) {
            throw new CommandErrorException();
        }
    }

    @Override
    public Message<GuardBuyContent> resolve() {
        // 获取上舰信息
        final Integer guardLevel = super.messageNode.get("data").get("guard_level").intValue();
        final Integer num = super.messageNode.get("data").get("num").intValue();

        final GuardBuyContent guardBuyContent = new GuardBuyContent(guardLevel, num);

        // 构建 Message
        final Message<GuardBuyContent> guardBuyContentMessage = new Message<>(rawMessageString,
                this.resolveMetadata(),
                MessageEnum.GUARD_BUY,
                guardBuyContent);

        return guardBuyContentMessage;
    }

    @Override
    protected UserInfo resolveUserInfo() {
        // 解析 UserInfo
        final Long uid = super.messageNode.get("data").get("uid").longValue();
        // 由于上舰的 key 是 username 需要单独处理
        final String username = super.messageNode.get("data").get("username").textValue();

        // 构建 UserInfoMetadata
        final UserInfo userInfo = new UserInfo(uid,
                username,
                "",
                this.resolveFansMedal());

        return userInfo;
    }

    @Override
    protected Long resolveTimestamp() {
        return Math.floorDiv(System.currentTimeMillis(), 1000);
    }
}
