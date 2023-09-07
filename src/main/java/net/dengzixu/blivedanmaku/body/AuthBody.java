package net.dengzixu.blivedanmaku.body;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public record AuthBody(@JsonProperty("uid") Long uid,
                       @JsonProperty("roomid") Long roomID,
                       @JsonProperty("protover") Integer protoVer,
                       @JsonProperty("platform") String platform,
                       @JsonProperty("type") Integer type,
                       @JsonProperty("key") String key) implements IBody {

    public AuthBody(Long uid, Long roomID, Integer protoVer, String platform, Integer type, String key) {
        this.uid = uid;
        this.roomID = roomID;
        this.protoVer = protoVer;
        this.platform = platform;
        this.type = type;
        this.key = key;
    }

    public AuthBody(Long uid, Long roomID, String key) {
        this(uid, roomID, 3, "web", 2, key);
    }

    public AuthBody(Long roomID, String key) {
        this(0L, roomID, 3, "web", 2, key);
    }

    public AuthBody(long roomID, String key) {
        this(0L, roomID, 3, "web", 2, key);
    }

    public String toJsonString() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public byte[] toJsonBytes() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.writeValueAsBytes(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
