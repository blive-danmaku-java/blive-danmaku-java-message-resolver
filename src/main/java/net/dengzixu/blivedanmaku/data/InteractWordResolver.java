package net.dengzixu.blivedanmaku.data;


import net.dengzixu.blivedanmaku.annotation.DataResolver;
import net.dengzixu.blivedanmaku.enums.CommandEnum;
import net.dengzixu.blivedanmaku.enums.MessageEnum;
import net.dengzixu.blivedanmaku.exception.CommandErrorException;
import net.dengzixu.blivedanmaku.message.Message;
import net.dengzixu.blivedanmaku.message.content.InteractWordContent;

@DataResolver(command = CommandEnum.INTERACT_WORD)
public class InteractWordResolver extends AbstractDataResolver<InteractWordContent> {
    public InteractWordResolver(String rawMessageString) {
        super(rawMessageString);

        if (!super.messageNode.get("cmd").textValue().equals("INTERACT_WORD")) {
            throw new CommandErrorException();
        }
    }

    @Override
    public Message<InteractWordContent> resolve() {
        // 获取 msg_type
        final int msgType = super.messageNode.get("data").get("msg_type").asInt();

        // 构建 InteractWordContent
        final InteractWordContent interactWordContent = new InteractWordContent(msgType);

        // 构建Message
        final Message<InteractWordContent> interactWordContentMessage = new Message<>(super.rawMessageString,
                this.resolveMetadata(),
                MessageEnum.INTERACT_WORD,
                interactWordContent);

        return interactWordContentMessage;
    }

}
