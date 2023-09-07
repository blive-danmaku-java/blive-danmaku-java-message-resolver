package net.dengzixu.blivedanmaku.message.content;

public record ComboSendContent(String action, String batchComboId, String batchComboNum,
                               Integer comboNum, Long comboTotalCoin, Long giftId,
                               String giftName) implements IMessageContent {

    public ComboSendContent(String action, String batchComboId, String batchComboNum,
                            Integer comboNum, long comboTotalCoin, long giftId,
                            String giftName) {
        this(action, batchComboId, batchComboNum, comboNum, Long.valueOf(comboTotalCoin), Long.valueOf(giftId), giftName);
    }

    @Override
    public String convertToString() {
        return null;
    }
}
