package net.dengzixu.blivedanmaku.message.content;

public record DanmuContent(String text, EmoticonContent emoticonContent) implements IMessageContent {
    @Override
    public String convertToString() {
        if (null != this.emoticonContent) {
            return "[表情包] " + text;
        } else {
            return text;
        }
    }

    public record EmoticonContent(String unique, String url) implements IMessageContent {
        @Override
        public String convertToString() {
            return null;
        }
    }
}
