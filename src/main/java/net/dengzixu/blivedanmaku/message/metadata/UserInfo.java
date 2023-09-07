package net.dengzixu.blivedanmaku.message.metadata;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserInfo(Long uid,
                       String username,
                       @JsonProperty("face_url")
                       String faceURL,
                       @JsonProperty("fans_medal")
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
