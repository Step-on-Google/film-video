import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.PropertyNamingStrategy;

import java.io.IOException;
import java.util.Iterator;

/**
 * @Auther: zhangjiachen
 * @Date: 2018/10/11 17:58
 * @Description:
 */
@Slf4j
public class JsonUtils {

    /**
     * 对象转换成下划线风格的字符串
     *
     * @param obj
     * @return
     */
    public static String camelObjToUnderLineJsonString(Object obj) {
        ObjectMapper objMapper = new ObjectMapper();
        objMapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        try {
            return objMapper.writeValueAsString(obj);
        } catch (Exception e) {
            log.error("camelToUnderLine error", e);
        }
        return null;
    }

    public static String transToUpperJsonKeyAndIgnoreBlank(String json) {
        JSONObject jsonObj2 = new JSONObject();
        JSONObject jsonObj = JSONObject.fromObject(json);
        Iterator it = jsonObj.keys();

        while (it.hasNext()) {
            String key = (String) it.next();
            String object = (null == jsonObj.get(key)) ? "" : (String) jsonObj.get(key);
            if (StringUtils.isNotBlank(object)) {
                jsonObj2.accumulate(key.toUpperCase(), jsonObj.get(key));
            }
        }
        return jsonObj2.toString();
    }

    /**
     * 将驼峰式字串转换为下划线分割
     *
     * @param input "AaaBbbCcc"
     * @return output “aaa_bbb_ccc”
     */
    public static String humpToUnderscores(String input) {
        StringBuffer buf = new StringBuffer(input);
        for (int i = 1; i < buf.length() - 1; i++) {
            if (Character.isLowerCase(buf.charAt(i - 1)) && Character.isUpperCase(buf.charAt(i))
                    && Character.isLowerCase(buf.charAt(i + 1))) {
                buf.insert(i++, '_');
            }
        }
        return buf.toString().toLowerCase();
    }

    /**
     * 下划线格式json字符串转换成驼峰式属性对象
     *
     * @param jsonStr
     * @param clazz
     * @return
     */
    public static <T> T underLineJsonStrToCamelObj(String jsonStr, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return mapper.readValue(jsonStr, clazz);
        } catch (JsonParseException e) {
            log.error("Json字符串转换成对象失败", e);
        } catch (JsonMappingException e) {
            log.error("Json字符串转换成对象失败", e);
        } catch (IOException e) {
            log.error("Json字符串转换成对象失败", e);
        }
        return null;
    }


    /**
     * 驼峰命名转为下划线命名
     *
     * @param para
     * @return
     */
    public static String humpToUnderline(String para) {
        StringBuilder sb = new StringBuilder(para);
        int temp = 0;
        for (int i = 0; i < para.length(); i++) {
            if (Character.isUpperCase(para.charAt(i))) {
                sb.insert(i + temp, "_");
                temp += 1;
            }
        }
        return sb.toString().toLowerCase();
    }

    /**
     * 对象转换为Json字符串 若转换失败，则返回null
     *
     * @param object
     * @return
     */
    public static String toJsonString(Object object) {
        return JSONObject.fromObject(object).toString();
    }
}
