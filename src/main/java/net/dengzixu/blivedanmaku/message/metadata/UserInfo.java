package net.dengzixu.blivedanmaku.message.metadata;

public record UserInfo(Long uid,
                       String username,
                       String faceURL,
                       FansMedal fansMedal) implements IMetadata {

    public UserInfo(long uid,
                    String username,
                    String faceURL,
                    FansMedal fansMedal) {
        this(Long.valueOf(uid), username, faceURL, fansMedal);
    }

    @Override
    public String convertToString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("[%s(%s)]", username, uid));

        if (null != fansMedal()) {
            stringBuilder.append(" ");
            stringBuilder.append(fansMedal.convertToString());
        }

        return stringBuilder.toString();
    }
}
