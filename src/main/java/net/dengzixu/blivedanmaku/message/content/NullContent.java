package net.dengzixu.blivedanmaku.message.content;

public record NullContent() implements IMessageContent {
    @Override
    public String convertToString() {
        return null;
    }
}