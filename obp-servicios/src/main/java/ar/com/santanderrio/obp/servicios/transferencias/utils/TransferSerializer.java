package ar.com.santanderrio.obp.servicios.transferencias.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;

import java.io.IOException;


public class TransferSerializer extends StdSerializer <LocalDate>{

    public TransferSerializer() {
        super(LocalDate.class);
    }


    @Override
    public void serialize(LocalDate value, JsonGenerator jgen, SerializerProvider provider) throws IOException{
        if (value != null) {
            jgen.writeString(value.toString(DateTimeFormat.forPattern("yyyy-MM-dd")));
        }
    }
}
