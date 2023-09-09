package net.dengzixu.blivedanmaku.message.content;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ComboSendContent(@JsonProperty("action")
                               String action,
                               @JsonProperty("batch_combo_id")
                               String batchComboId,
                               @JsonProperty("batch_combo_num")
                               String batchComboNum,
                               @JsonProperty("combo_num")
                               Integer comboNum,
                               @JsonProperty("combo_total_coin")
                               Long comboTotalCoin,
                               @JsonProperty("gift_id")
                               Long giftId,
                               @JsonProperty("gift_name")
                               String giftName) implements IMessageContent {

    public ComboSendContent(String action,
                            String batchComboId,
                            String batchComboNum,
                            Integer comboNum,
                            long comboTotalCoin,
                            long giftId,
                            String giftName) {
        this(action, batchComboId, batchComboNum, comboNum, Long.valueOf(comboTotalCoin), Long.valueOf(giftId), giftName);
    }

    @Override
    public String convertToString() {
        return null;
    }
}
