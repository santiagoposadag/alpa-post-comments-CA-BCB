package co.com.alpha.bcb.serializer;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.logging.Logger;

public class JSONMapperImpl implements JSONMapper {

    Logger logger = Logger.getLogger("Json-mapper-logger");
    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    @Override
    public String writeToJson(Object object){
        try{
            return mapper.writeValueAsString(object);
        }catch (JsonProcessingException e ){
            logger.warning(e.getMessage());
            return "";
        }catch (ClassCastException e){
            logger.warning(e.getMessage());
            return "";
        }

    }

    @Override
    public Object readFromJson(String json, Class<?> clazz){
        try {
            return mapper.readValue(json, clazz);
        }catch (JsonProcessingException e ){
            logger.warning(e.getMessage());
            return "";
        }catch (ClassCastException e){
            logger.warning(e.getMessage());
            return "";
        }
    }
}
