package net.dengzixu.blivedanmaku.message.metadata;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Metadata(
        @JsonProperty("send_time")
        String sendTime,
        @JsonProperty("message_id")
        String messageID,
        @JsonProperty("user_info")
        UserInfo userInfo,
        @JsonProperty("timestamp")
        Long timestamp) implements IMetadata {

    public Metadata(Long sendTime,
                    String messageID,
                    UserInfo userInfo,
                    Long timestamp) {
        this(sendTime.toString(), messageID, userInfo, timestamp);
    }


    @Override
    public String convertToString() {
        return null != userInfo ? userInfo.convertToString() : null;
    }
}
