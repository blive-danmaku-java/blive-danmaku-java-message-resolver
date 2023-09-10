package net.dengzixu.blivedanmaku.data;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.dengzixu.blivedanmaku.exception.DataFormatException;
import net.dengzixu.blivedanmaku.message.Message;
import net.dengzixu.blivedanmaku.message.content.IMessageContent;
import net.dengzixu.blivedanmaku.message.metadata.FansMedal;
import net.dengzixu.blivedanmaku.message.metadata.Metadata;
import net.dengzixu.blivedanmaku.message.metadata.UserInfo;

public abstract class AbstractDataResolver<T extends IMessageContent> {
    // Logger
    private static final org.slf4j.Logger Logger = org.slf4j.LoggerFactory.getLogger(AbstractDataResolver.class);

    // 原始消息
    protected final String rawMessageString;

    protected final JsonNode messageNode;

    public AbstractDataResolver(String rawMessageString) {
        this.rawMessageString = rawMessageString;
        try {
            messageNode = new ObjectMapper().readValue(rawMessageString, JsonNode.class);
        } catch (JsonProcessingException e) {
            Logger.error("数据解析失败", e);
            throw new DataFormatException();
        }
    }

    public abstract Message<T> resolve();

    protected Metadata resolveMetadata() {
        final Metadata metadata = new Metadata(this.resolveSendTime(),
                this.resolveMessageID(),
                this.resolveUserInfo(),
                this.resolveTimestamp());

        return metadata;
    }

    protected UserInfo resolveUserInfo() {
        // 解析 UserInfo
        if (!this.messageNode.get("data").hasNonNull("uid")) {
            return null;
        }

        final Long uid = this.messageNode.get("data").get("uid").longValue();
        final String username = this.messageNode.get("data").get("uname").textValue();

        // 构建 UserInfoMetadata
        final UserInfo userMetadata = new UserInfo(uid,
                username,
                null,
                this.resolveFansMedal(this.messageNode.get("data").get("fans_medal")));

        return userMetadata;
    }

    protected FansMedal resolveFansMedal() {
        return null;
    }

    protected FansMedal resolveFansMedal(JsonNode fansMedalNode) {
        if (null == fansMedalNode
                || fansMedalNode.isEmpty()
                || fansMedalNode.hasNonNull("target_id")
                || fansMedalNode.get("target_id").intValue() == 0) {
            return null;
        }

        final FansMedal fansMedal = new FansMedal(
                fansMedalNode.get("target_id").longValue(),
                fansMedalNode.get("medal_level").intValue(),
                fansMedalNode.get("medal_name").textValue(),
                fansMedalNode.get("medal_color").intValue(),
                fansMedalNode.get("medal_color_start").intValue(),
                fansMedalNode.get("medal_color_end").intValue(),
                fansMedalNode.get("medal_color_border").intValue(),
                fansMedalNode.get("is_lighted").intValue() == 1,
                fansMedalNode.get("guard_level").intValue(),
                fansMedalNode.get("special").textValue(),
                fansMedalNode.get("icon_id").intValue(),
                fansMedalNode.get("anchor_roomid").longValue(),
                fansMedalNode.hasNonNull("score") ? fansMedalNode.get("score").intValue() : 0);

        return fansMedal;
    }

    protected Long resolveTimestamp() {
        return messageNode.get("data").get("timestamp").longValue();
    }

    protected Long resolveSendTime() {
        if (!messageNode.hasNonNull("send_time")) {
            return -1L;
        }

        final Long sendTime = messageNode.get("send_time").longValue();

        return sendTime;
    }

    protected String resolveMessageID() {
        if (!messageNode.hasNonNull("msg_id")) {
            return "-1";
        }

        final String messageID = messageNode.get("msg_id").textValue();

        return messageID;
    }
}
