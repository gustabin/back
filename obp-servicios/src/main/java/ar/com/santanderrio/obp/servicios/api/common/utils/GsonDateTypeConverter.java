package ar.com.santanderrio.obp.servicios.api.common.utils;

import com.google.gson.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.lang.reflect.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;

public class GsonDateTypeConverter implements JsonSerializer<Date>, JsonDeserializer<Date> {
    private static final Logger logger = LoggerFactory.getLogger(GsonDateTypeConverter.class);
    protected static final DateTimeFormatter DEFAULT_FORMATTER = DateTimeFormat.forPattern("yyyy-MM-dd");

    protected final DateTimeFormatter serializationDateTimeFormatter;
    protected final DateTimeFormatter deserializationDateTimeFormatter;


    public GsonDateTypeConverter(DateTimeFormatter deserializationDateTimeFormatter, DateTimeFormatter serializationDateTimeFormatter) {
        this.deserializationDateTimeFormatter = deserializationDateTimeFormatter;
        this.serializationDateTimeFormatter = serializationDateTimeFormatter;
    }

    public GsonDateTypeConverter() {
        this(DEFAULT_FORMATTER, null);
    }

    public GsonDateTypeConverter withSerializationFormat(DateTimeFormatter serializationDateTimeFormatter) {
        return new GsonDateTypeConverter(this.deserializationDateTimeFormatter, serializationDateTimeFormatter);
    }

    public GsonDateTypeConverter withDeserializationFormat(DateTimeFormatter deserializationDateTimeFormatter) {
        return new GsonDateTypeConverter(deserializationDateTimeFormatter, this.serializationDateTimeFormatter);
    }

    @Override
    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context) {
        if (serializationDateTimeFormatter != null) {
            return new JsonPrimitive(serializationDateTimeFormatter.print(src.getTime()));
        } else {
            return new JsonPrimitive(src.toString());
        }
    }

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return deserializationDateTimeFormatter.parseDateTime(json.getAsString()).toDate();
        } catch (Exception ex) {
            logger.error("Error - Date couldnt be deserialized");
            throw new JsonParseException(ex);
        }
    }
}
