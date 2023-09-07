package net.dengzixu.blivedanmaku.data;


import net.dengzixu.blivedanmaku.annotation.DataResolver;
import net.dengzixu.blivedanmaku.enums.CommandEnum;
import net.dengzixu.blivedanmaku.enums.MessageEnum;
import net.dengzixu.blivedanmaku.message.Message;
import net.dengzixu.blivedanmaku.message.content.NullContent;
import net.dengzixu.blivedanmaku.message.metadata.UserInfo;

@DataResolver(command = {
        CommandEnum.COMBO_SEND,
        CommandEnum.POPULARITY_RED_POCKET_WINNER_LIST,
        CommandEnum.ROOM_REAL_TIME_MESSAGE_UPDATE,
        CommandEnum.POPULARITY_RED_POCKET_NEW,
        CommandEnum.WIDGET_GIFT_STAR_PROCESS,
        CommandEnum.HOT_RANK_SETTLEMENT_V2,
        CommandEnum.LIVE_INTERACTIVE_GAME,
        CommandEnum.COMMON_NOTICE_DANMAKU,
        CommandEnum.GUARD_HONOR_THOUSAND,
        CommandEnum.HOT_RANK_SETTLEMENT,
        CommandEnum.STOP_LIVE_ROOM_LIST,
        CommandEnum.HOT_RANK_CHANGED_V2,
        CommandEnum.LIKE_INFO_V3_UPDATE,
        CommandEnum.LIKE_INFO_V3_CLICK,
        CommandEnum.LIVE_PANEL_CHANGE,
        CommandEnum.ONLINE_RANK_COUNT,
        CommandEnum.DANMU_AGGREGATION,
        CommandEnum.HOT_RANK_CHANGED,
        CommandEnum.ONLINE_RANK_TOP3,
        CommandEnum.USER_TOAST_MSG,
        CommandEnum.ONLINE_RANK_V2,
        CommandEnum.WIDGET_BANNER,
        CommandEnum.NOTICE_MSG
})
public class IgnoreResolver extends AbstractDataResolver<NullContent> {
    public IgnoreResolver(String rawMessageString) {
        super(rawMessageString);
    }

    @Override
    public Message<NullContent> resolve() {
        final Message<NullContent> message = new Message<>(rawMessageString,
                this.resolveMetadata(),
                MessageEnum.IGNORE,
                null);

        return message;
    }

    @Override
    protected UserInfo resolveUserInfo() {
        return null;
    }

    @Override
    protected Long resolveTimestamp() {
        return -1L;
    }
}
