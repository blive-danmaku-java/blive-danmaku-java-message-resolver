package net.dengzixu.blivedanmaku.message.metadata;

public record FansMedal(Long targetId,
                        Integer level,
                        String name,
                        Integer color,
                        Integer colorStart,
                        Integer colorEnd,
                        Integer colorBorder,
                        Boolean lighted,
                        Integer guardLevel,
                        String special,
                        Integer iconId,
                        Long anchorRoomId,
                        Long score) implements IMetadata {

    /**
     * @param targetId     粉丝牌对应主播 UID
     * @param level        粉丝牌等级
     * @param name         粉丝牌名称
     * @param color        粉丝牌颜色
     * @param colorStart   粉丝牌过渡色开始颜色
     * @param colorEnd     粉丝牌过渡色结束颜色
     * @param colorBorder  粉丝牌边框颜色
     * @param lighted      是否点亮
     * @param guardLevel   大航海等级
     *                     3: 舰长
     *                     2: 提督
     *                     1: 总督
     * @param special      未知字段
     * @param iconId       粉丝牌图标 ID
     * @param anchorRoomId 粉丝牌对应直播间房间号
     * @param score        粉丝牌原力值
     */
    public FansMedal(long targetId,
                     Integer level,
                     String name,
                     Integer color,
                     Integer colorStart,
                     Integer colorEnd,
                     Integer colorBorder,
                     Boolean lighted,
                     Integer guardLevel,
                     String special,
                     Integer iconId,
                     long anchorRoomId,
                     long score) {
        this(Long.valueOf(targetId),
                level,
                name,
                color,
                colorStart,
                colorEnd,
                colorBorder,
                lighted,
                guardLevel,
                special,
                iconId,
                Long.valueOf(anchorRoomId),
                Long.valueOf(score));
    }


    @Override
    public String convertToString() {
        if (null == name || name.isEmpty()) {
            return null;
        }

        return String.format("[%s-%s]", name, level);
    }
}
