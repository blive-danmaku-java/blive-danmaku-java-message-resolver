package net.dengzixu.blivedanmaku.message.content;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DanmuContent(@JsonProperty("text")
                           String text,
                           @JsonProperty("emoticon_content")
                           EmoticonContent emoticonContent) implements IMessageContent {
    @Override
    public String convertToString() {
        if (null != this.emoticonContent) {
            return "[表情包] " + text;
        } else {
            return text;
        }
    }

    public record EmoticonContent(@JsonProperty("unique")
                                  String unique,
                                  @JsonProperty("url")
                                  String url) implements IMessageContent {
        @Override
        public String convertToString() {
            return null;
        }
    }
}
