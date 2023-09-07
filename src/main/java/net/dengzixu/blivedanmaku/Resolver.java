package net.dengzixu.blivedanmaku;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.dengzixu.blivedanmaku.annotation.processor.DataResolverAnnotationProcessor;
import net.dengzixu.blivedanmaku.enums.CommandEnum;
import net.dengzixu.blivedanmaku.enums.MessageEnum;
import net.dengzixu.blivedanmaku.exception.DataFormatException;
import net.dengzixu.blivedanmaku.message.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;

public class Resolver {
    // Logger
    private static final Logger logger = LoggerFactory.getLogger(Resolver.class);

    private final String rawMessageString;

    public Resolver(String rawMessageString) {
        this.rawMessageString = rawMessageString;
    }

    public Message<?> resolve() {


        try {
            JsonNode messageJsonNode = new ObjectMapper().readValue(rawMessageString, JsonNode.class);

            String cmd = messageJsonNode.get("cmd").textValue();

            if (cmd.contains("DANMU_MSG")) {
                cmd = "DANMU_MSG";
            }

            // 获取对应的 Resolver
            Class<?> clazz = DataResolverAnnotationProcessor.COMMAND_MAP.get(CommandEnum.valueOf(cmd));

            // 判断是否存在对应的 Resolver
            if (null != clazz) {
                Object instance = clazz.getDeclaredConstructor(String.class).newInstance(rawMessageString);

                Message<?> message = (Message<?>) clazz.getMethod("resolve").invoke(instance);

                return message;

            } else {
                logger.warn("找不到[{}]对应的 Resolver", cmd);

                return new Message<>(rawMessageString, null, null);

            }
        } catch (JsonProcessingException e) {
            logger.error("数据解析错误，原始数据: {}", rawMessageString, e);
            throw new DataFormatException();
        } catch (InvocationTargetException |
                 InstantiationException |
                 IllegalAccessException |
                 NoSuchMethodException e) {
            logger.error("获取 DataResolver 实例错误", e);
        } catch (IllegalArgumentException e) {
            logger.warn("找不到对应的 Command, {}", e.getMessage());
            return new Message<>(rawMessageString, null, MessageEnum.UNKNOWN, null);
//            throw new UnknownCommandException();
        }


        return null;
    }


}
