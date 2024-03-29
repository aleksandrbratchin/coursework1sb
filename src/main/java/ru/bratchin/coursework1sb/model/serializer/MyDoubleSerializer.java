package ru.bratchin.coursework1sb.model.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.text.DecimalFormat;

public class MyDoubleSerializer extends StdSerializer<Double> {
    protected MyDoubleSerializer(Class<Double> t) {
        super(t);
    }

    protected MyDoubleSerializer() {
        this(null);
    }

    @Override
    public void serialize(Double aDouble, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeString(aDouble != null ?
                new DecimalFormat("#0.00").format(aDouble).replace(",", ".") : "");
    }

}
