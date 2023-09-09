package net.dengzixu.blivedanmaku.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import net.dengzixu.blivedanmaku.enums.MessageEnum;
import net.dengzixu.blivedanmaku.message.content.IMessageContent;
import net.dengzixu.blivedanmaku.message.metadata.Metadata;

public record Message<T extends IMessageContent>(@JsonProperty("raw_message")
                                                 String rawMessage,
                                                 @JsonProperty("message_type")
                                                 MessageEnum messageEnum,
                                                 @JsonProperty("metadata")
                                                 Metadata metadata,
                                                 @JsonProperty("content")
                                                 T content) implements IMessage {
    public Message(String rawMessage, Metadata metadata, T content) {
        this(rawMessage, MessageEnum.UNKNOWN, metadata, content);
    }

    public Message(String rawMessage, Metadata metadata, MessageEnum messageEnum, T content) {
        this(rawMessage, messageEnum, metadata, content);
    }

    @Override
    public String convertToString() {
        StringBuilder stringBuilder = new StringBuilder();

        if (null != metadata && null != metadata.convertToString()) {
            stringBuilder.append(metadata.convertToString());
            stringBuilder.append(" ");
        }

        if (null == content) {
            return null;
        }

        stringBuilder.append(content.convertToString());

        return stringBuilder.toString();
    }
}