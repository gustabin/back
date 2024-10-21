package ar.com.santanderrio.obp.servicios.ws.jackson;

import ar.com.santanderrio.obp.servicios.api.common.utils.CompositeGsonDateTypeConverter;
import ar.com.santanderrio.obp.servicios.api.common.utils.GsonDateTypeConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.DateTimeFormatterBuilder;
import org.joda.time.format.DateTimeParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;

public class FormattedDateSerializer extends JsonSerializer<Date> {
    private static final Logger logger = LoggerFactory.getLogger(FormattedDateSerializer.class);
    DateTimeFormatter formatWithTimezone = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZZ").withZoneUTC();
    DateTimeFormatter localDateFormat = DateTimeFormat.forPattern("yyyy-MM-dd");

    DateTimeFormatter compositeFormatter = new DateTimeFormatterBuilder()
            .append(
                    formatWithTimezone.getPrinter(),
                    new DateTimeParser[]{ formatWithTimezone.getParser(), localDateFormat.getParser()}
            ).toFormatter();

    GsonDateTypeConverter dateTypeConverter = new CompositeGsonDateTypeConverter()
            .withDeserializationFormat(compositeFormatter)
            .withSerializationFormat(compositeFormatter);
    Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, dateTypeConverter).create();

    @Override
    public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {
        try {
            String date = formatWithTimezone.print(value.getTime());
            jgen.writeString(date);
        } catch (Exception exception) {
            //silently digest
            logger.info("could not serialize date. printing as timestamp");
            jgen.writeString(value.toString());
        }
    }
}
