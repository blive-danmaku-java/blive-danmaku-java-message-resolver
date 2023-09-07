package net.dengzixu.blivedanmaku.data;


import net.dengzixu.blivedanmaku.annotation.DataResolver;
import net.dengzixu.blivedanmaku.enums.CommandEnum;
import net.dengzixu.blivedanmaku.enums.MessageEnum;
import net.dengzixu.blivedanmaku.exception.CommandErrorException;
import net.dengzixu.blivedanmaku.message.Message;
import net.dengzixu.blivedanmaku.message.content.WatchedChangeContent;
import net.dengzixu.blivedanmaku.message.metadata.UserInfo;

@DataResolver(command = CommandEnum.WATCHED_CHANGE)
public class WatchedChangeResolver extends AbstractDataResolver<WatchedChangeContent> {
    public WatchedChangeResolver(String rawMessageString) {
        super(rawMessageString);

        if (!super.messageNode.get("cmd").textValue().equals("WATCHED_CHANGE")) {
            throw new CommandErrorException();
        }
    }

    @Override
    public Message<WatchedChangeContent> resolve() {
        final WatchedChangeContent watchedChangeContent = new WatchedChangeContent(
                messageNode.get("data").get("num").intValue(),
                messageNode.get("data").get("text_small").textValue(),
                messageNode.get("data").get("text_large").textValue());

        final Message<WatchedChangeContent> watchedChangeContentMessage = new Message<>(rawMessageString,
                this.resolveMetadata(),
                MessageEnum.WATCHED_CHANGE,
                watchedChangeContent);

        return watchedChangeContentMessage;
    }

    @Override
    protected UserInfo resolveUserInfo() {
        return null;
    }

    @Override
    protected Long resolveTimestamp() {
        return Math.floorDiv(System.currentTimeMillis(), 1000);
    }
}
