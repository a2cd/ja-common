package org.caseor.common.core.util;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.caseor.common.core.exception.ServiceException;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Json工具类
 * @author Fu Kai
 * @since 20210111
 */

public class JsonUtil {

    enum ObjectMapperSingletonEnum {
        /**
         * 单实例
         */
        INSTANCE;
        private final ObjectMapper objectMapper;

        ObjectMapperSingletonEnum() {
            objectMapper = new ObjectMapper();
            JavaTimeModule javaTimeModule = new JavaTimeModule();
            javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DateUtil.CH_S)));
            javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DateUtil.CH_D)));
            javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DateUtil.CH_SS)));

            javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DateUtil.CH_S)));
            javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DateUtil.CH_D)));
            javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DateUtil.CH_SS)));
            objectMapper.registerModule(new ParameterNamesModule())
                    .registerModule(new Jdk8Module())
                    .registerModule(javaTimeModule)
                    // 允许解析不带引号的JSON(不带引号的JSON不是标准的JSON)
                    .configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true)
                    // 允许单引号
                    .configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        }

        public ObjectMapper instance() {
            return objectMapper;
        }
    }

    private JsonUtil() {
    }

    public static ObjectMapper instance() {
        return ObjectMapperSingletonEnum.INSTANCE.instance();
    }

    public static ObjectWriter writer() {
        return instance().writer();
    }

    public static ObjectReader reader() {
        return instance().reader();
    }

    /**
     * Serialize Object -> jsonString
     */
    public static String serialize(Object jsonObject) {
        if (null == jsonObject) {
            return null;
        }
        String jsonString;
        try {
            jsonString = instance().writeValueAsString(jsonObject);
        } catch (JsonProcessingException e) {
            throw new ServiceException("JsonUtil - serialize() - JsonProcessingException");
        }
        return jsonString;
    }

    /**
     * Deserialize one object
     * jsonString -> Object
     */
    public static <T> T deserializeObject(String jsonString, Class<T> type) {
        if (null == jsonString) {
            return null;
        }
        T jsonObject;
        try {
            jsonObject = instance().readValue(jsonString, type);
        } catch (JsonProcessingException e) {
            throw new ServiceException("JsonUtil - deserialize() - JsonProcessingException");
        }
        return jsonObject;
    }


    /**
     * Deserialize jsonListString -> ObjectList
     */
    public static <T> List<T> deserializeList(String jsonString, Class<T> parameterClass) {
        if (null == jsonString) {
            return null;
        }
        List<T> objectList;
        CollectionType listType = instance().getTypeFactory().constructCollectionType(ArrayList.class, parameterClass);
        try {
            objectList = instance().readValue(jsonString, listType);
        } catch (JsonProcessingException e) {
            throw new ServiceException("JsonUtil - deserializeList() - JsonProcessingException");
        }
        return objectList;
    }


    /**
     * Deserialize jsonMapString -> ObjectMap
     */
    public static <T> Set<T> deserializeSet(String jsonString, Class<T> elementClass) {
        if (null == jsonString) {
            return null;
        }
        Set<T> set;
        JavaType javaType = instance().getTypeFactory().constructParametricType(HashSet.class, elementClass);
        try {
            set = instance().readValue(jsonString, javaType);
        } catch (IOException e) {
            throw new ServiceException("JsonUtil - deserializeSet() - IOException");
        }
        return set;
    }


    public static <K, V> Map<K, V> deserializeMap(String jsonString, Class<K> keyClass, Class<V> valueClass) {
        if (null == jsonString) {
            return null;
        }
        Map<K, V> objectMap;
        JavaType javaType = instance().getTypeFactory().constructParametricType(HashMap.class, keyClass, valueClass);
        try {
            objectMap = instance().readValue(jsonString, javaType);
        } catch (IOException e) {
            throw new ServiceException("JsonUtil - deserializeMap() - IOException");
        }
        return objectMap;
    }

    /**
     * jsonString -> jsonNode
     */
    public static JsonNode toJsonNode(String jsonString) {
        if (null == jsonString) {
            return null;
        }
        JsonNode jsonNode;
        try {
            jsonNode = instance().readTree(jsonString);
        } catch (JsonProcessingException e) {
            throw new ServiceException("JsonUtil - toJsonNode() - IOException");
        }
        return jsonNode;
    }

    /**
     * ObjectNode
     */
    public static ObjectNode objectNode() {
        return instance().createObjectNode();
    }

    /**
     * ArrayNode
     */
    public static ArrayNode arrayNode() {
        return instance().createArrayNode();
    }

}
