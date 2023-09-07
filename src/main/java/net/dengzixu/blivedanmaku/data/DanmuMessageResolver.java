package net.dengzixu.blivedanmaku.data;


import com.fasterxml.jackson.databind.JsonNode;
import net.dengzixu.blivedanmaku.annotation.DataResolver;
import net.dengzixu.blivedanmaku.enums.CommandEnum;
import net.dengzixu.blivedanmaku.enums.MessageEnum;
import net.dengzixu.blivedanmaku.exception.CommandErrorException;
import net.dengzixu.blivedanmaku.message.Message;
import net.dengzixu.blivedanmaku.message.content.DanmuContent;
import net.dengzixu.blivedanmaku.message.metadata.FansMedal;
import net.dengzixu.blivedanmaku.message.metadata.UserInfo;

@DataResolver(command = CommandEnum.DANMU_MSG)
public class DanmuMessageResolver extends AbstractDataResolver<DanmuContent> {
    private static final int BASE_INFO_INDEX = 0;
    private static final int BASE_INFO_IS_EMOTICON_INDEX = 12;
    private static final int BASE_INFO_EMOTICON_INFO_INDEX = 13;

    private static final int DANMU_TEXT_INDEX = 1;

    private static final int USER_INFO_INDEX = 2;
    private static final int USER_INFO_UID_INDEX = 0;
    private static final int USER_INFO_NAME_INDEX = 1;

    private static final int FANS_MEDAL_INDEX = 3;
    private static final int FANS_MEDAL_TARGET_ID_INDEX = 12;
    private static final int FANS_MEDAL_LEVEL_INDEX = 0;
    private static final int FANS_MEDAL_NAME_INDEX = 1;
    private static final int FANS_MEDAL_color_INDEX = 4;
    private static final int FANS_MEDAL_COLOR_START_INDEX = 8;
    private static final int FANS_MEDAL_COLOR_END_INDEX = 9;
    private static final int FANS_MEDAL_COLOR_BORDER_INDEX = 7;
    private static final int FANS_MEDAL_LIGHTED_INDEX = 11;
    private static final int FANS_MEDAL_GUARD_LEVEL_INDEX = 10;
    private static final int FANS_MEDAL_SPECIAL_INDEX = 5;
    private static final int FANS_MEDAL_ICON_ID_INDEX = 6;
    private static final int FANS_MEDAL_ANCHOR_ROOM_ID_INDEX = 3;

    public DanmuMessageResolver(String rawMessageString) {
        super(rawMessageString);
    }

    @Override
    public Message<DanmuContent> resolve() {
        if (!super.messageNode.get("cmd").textValue().equals("DANMU_MSG")) {
            throw new CommandErrorException();
        }

        // BaseInfo
        final JsonNode baseInfoNode = super.messageNode.get("info").get(BASE_INFO_INDEX);

        // 获取弹幕信息
        final String danmuText = super.messageNode.get("info").get(DANMU_TEXT_INDEX).asText();

        // 判断是否为表情包弹幕
        DanmuContent.EmoticonContent emoticonContent = null;
        if (baseInfoNode.get(BASE_INFO_IS_EMOTICON_INDEX).asInt() == 1) {

            final JsonNode emoticonJsonNode = baseInfoNode.get(BASE_INFO_EMOTICON_INFO_INDEX);

            final String emoticonUnique = emoticonJsonNode.get("emoticon_unique").asText();
            final String url = emoticonJsonNode.get("url").asText();

            emoticonContent = new DanmuContent.EmoticonContent(emoticonUnique, url);
        }

        final DanmuContent danmuContent = new DanmuContent(danmuText, emoticonContent);


        // 构建 Message
        final Message<DanmuContent> danmuContentMessage = new Message<>(rawMessageString,
                this.resolveMetadata(),
                MessageEnum.DANMU_MSG,
                danmuContent);

        return danmuContentMessage;
    }

    @Override
    protected UserInfo resolveUserInfo() {
        // 解析 UserInfo
        final JsonNode userInfoNode = super.messageNode.get("info").get(USER_INFO_INDEX);

        final Long uid = userInfoNode.get(USER_INFO_UID_INDEX).longValue();
        final String username = userInfoNode.get(USER_INFO_NAME_INDEX).textValue();

        // 构建 UserInfoMetadata
        final UserInfo userMetadata = new UserInfo(uid, username, null, this.resolveFansMedal());

        return userMetadata;
    }

    @Override
    protected FansMedal resolveFansMedal() {
        // 解析 FansMedal
        final JsonNode fansMedalNode = super.messageNode.get("info").get(FANS_MEDAL_INDEX);

        FansMedal fansMedal = null;

        if (!fansMedalNode.isEmpty()) {
            fansMedal = new FansMedal(fansMedalNode.get(FANS_MEDAL_TARGET_ID_INDEX).longValue(),
                    fansMedalNode.get(FANS_MEDAL_LEVEL_INDEX).intValue(),
                    fansMedalNode.get(FANS_MEDAL_NAME_INDEX).textValue(),
                    fansMedalNode.get(FANS_MEDAL_color_INDEX).intValue(),
                    fansMedalNode.get(FANS_MEDAL_COLOR_START_INDEX).intValue(),
                    fansMedalNode.get(FANS_MEDAL_COLOR_END_INDEX).intValue(),
                    fansMedalNode.get(FANS_MEDAL_COLOR_BORDER_INDEX).intValue(),
                    fansMedalNode.get(FANS_MEDAL_LIGHTED_INDEX).intValue() == 1,
                    fansMedalNode.get(FANS_MEDAL_GUARD_LEVEL_INDEX).intValue(),
                    fansMedalNode.get(FANS_MEDAL_SPECIAL_INDEX).textValue(),
                    fansMedalNode.get(FANS_MEDAL_ICON_ID_INDEX).intValue(),
                    fansMedalNode.get(FANS_MEDAL_ANCHOR_ROOM_ID_INDEX).longValue(),
                    0);
        }

        return fansMedal;
    }

    @Override
    protected Long resolveTimestamp() {
        long timestamp = messageNode.get("info").get(0).get(4).asLong();

        return Math.floorDiv(timestamp, 1000);
    }
}
