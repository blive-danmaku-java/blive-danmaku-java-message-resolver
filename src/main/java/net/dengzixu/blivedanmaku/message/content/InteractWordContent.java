package net.dengzixu.blivedanmaku.message.content;

import com.fasterxml.jackson.annotation.JsonProperty;

public record InteractWordContent(@JsonProperty("msg_type")
                                  Integer msgType) implements IMessageContent {
    @Override
    public String convertToString() {
        return switch (msgType) {
            case 1 -> "进入直播间";
            case 2 -> "关注直播间";
            case 3 -> "分享直播间";
            case 4 -> "特别关注直播间";
            case 5 -> "互相关注直播间";
            default -> throw new IllegalStateException("Unexpected value: " + msgType);
        };
    }
}
