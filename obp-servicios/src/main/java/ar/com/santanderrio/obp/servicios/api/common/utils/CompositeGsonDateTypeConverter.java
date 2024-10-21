package ar.com.santanderrio.obp.servicios.api.common.utils;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Type;
import java.util.Date;

public class CompositeGsonDateTypeConverter extends GsonDateTypeConverter {
    private static final Logger logger = LoggerFactory.getLogger(CompositeGsonDateTypeConverter.class);

    public CompositeGsonDateTypeConverter() {
        super();
    }

    public CompositeGsonDateTypeConverter(DateTimeFormatter deserializationDateTimeFormatter, DateTimeFormatter serializationDateTimeFormatter) {
        super(deserializationDateTimeFormatter, serializationDateTimeFormatter);
    }

    public CompositeGsonDateTypeConverter withSerializationFormat(DateTimeFormatter serializationDateTimeFormatter) {
        return new CompositeGsonDateTypeConverter(this.deserializationDateTimeFormatter, serializationDateTimeFormatter);
    }

    public CompositeGsonDateTypeConverter withDeserializationFormat(DateTimeFormatter deserializationDateTimeFormatter) {
        return new CompositeGsonDateTypeConverter(deserializationDateTimeFormatter, this.serializationDateTimeFormatter);
    }

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            if (json.isJsonPrimitive() && json.getAsJsonPrimitive().isNumber()) {
               return new Date(json.getAsLong());
            }
            return deserializationDateTimeFormatter.parseDateTime(json.getAsString()).toDate();
        } catch (Exception ex) {
            logger.error("Error - Date couldnt be deserialized");
            throw new JsonParseException(ex);
        }
    }
}
