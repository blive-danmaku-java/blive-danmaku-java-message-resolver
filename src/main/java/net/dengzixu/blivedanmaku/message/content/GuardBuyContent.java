package net.dengzixu.blivedanmaku.message.content;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GuardBuyContent(@JsonProperty("guard_level")
                              Integer guardLevel,
                                @JsonProperty("num")
                              Integer num) implements IMessageContent {
    @Override
    public String convertToString() {

        StringBuilder stringBuilder = new StringBuilder();

        switch (guardLevel) {
            case 1 -> stringBuilder.append("购买 总督 x ");
            case 2 -> stringBuilder.append("购买 提督 x ");
            case 3 -> stringBuilder.append("购买 舰长 x ");
        }
        stringBuilder.append(num);

        return stringBuilder.toString();
    }
}
