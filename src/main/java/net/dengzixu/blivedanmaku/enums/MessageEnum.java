package net.dengzixu.blivedanmaku.enums;

/**
 * 消息类型
 * 消息类型绝大多数与 Command 相同
 * 以下划线开头的为自定义的消息类型
 */
public enum MessageEnum {
    DANMU_MSG,
    SEND_GIFT,
    GUARD_BUY,
    COMBO_SEND,
    ENTRY_EFFECT,
    INTERACT_WORD,
    WATCHED_CHANGE,
    ROOM_BLOCK_MSG,

    UNKNOWN,
    IGNORE

}
