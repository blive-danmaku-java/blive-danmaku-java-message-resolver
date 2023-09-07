package net.dengzixu.blivedanmaku.data;


import net.dengzixu.blivedanmaku.annotation.DataResolver;
import net.dengzixu.blivedanmaku.enums.CommandEnum;
import net.dengzixu.blivedanmaku.enums.MessageEnum;
import net.dengzixu.blivedanmaku.exception.CommandErrorException;
import net.dengzixu.blivedanmaku.message.Message;
import net.dengzixu.blivedanmaku.message.content.SendGiftContent;
import net.dengzixu.blivedanmaku.message.metadata.UserInfo;

@DataResolver(command = CommandEnum.SEND_GIFT)
public class SendGiftResolver extends AbstractDataResolver<SendGiftContent> {
    public SendGiftResolver(String rawMessageString) {
        super(rawMessageString);

        if (!super.messageNode.get("cmd").textValue().equals("SEND_GIFT")) {
            throw new CommandErrorException();
        }
    }

    @Override
    public Message<SendGiftContent> resolve() {
        final SendGiftContent sendGiftContent = new SendGiftContent(
                messageNode.get("data").get("action").textValue(),
                messageNode.get("data").get("batch_combo_id").textValue(),
                messageNode.get("data").get("batch_combo_send").textValue(),
                messageNode.get("data").get("coin_type").textValue(),
                messageNode.get("data").get("discount_price").intValue(),
                messageNode.get("data").get("giftId").longValue(),
                messageNode.get("data").get("giftName").textValue(),
                messageNode.get("data").get("giftType").intValue(),
                messageNode.get("data").get("is_first").booleanValue(),
                messageNode.get("data").get("num").intValue(),
                messageNode.get("data").get("price").intValue(),
                messageNode.get("data").get("total_coin").longValue()
        );

        final Message<SendGiftContent> sendGiftContentMessage = new Message<>(rawMessageString,
                this.resolveMetadata(),
                MessageEnum.SEND_GIFT,
                sendGiftContent);

        return sendGiftContentMessage;
    }

    @Override
    protected UserInfo resolveUserInfo() {
        // 解析 UserInfo
        if (!this.messageNode.get("data").hasNonNull("uid")) {
            return null;
        }

        final Long uid = this.messageNode.get("data").get("uid").longValue();
        final String username = this.messageNode.get("data").get("uname").textValue();

        // 构建 UserInfoMetadata 这里得物 FansMedal的key特殊 需要单独解析
        final UserInfo userMetadata = new UserInfo(uid,
                username,
                "",
                this.resolveFansMedal(this.messageNode.get("data").get("medal_info")));

        return userMetadata;
    }
}
