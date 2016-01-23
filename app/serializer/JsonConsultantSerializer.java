package serializer;

import java.io.IOException;

import models.Consultant;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonConsultantSerializer extends JsonSerializer<Consultant> {

    @Override
    public void serialize(
    		Consultant consultant, JsonGenerator jgen, SerializerProvider provider) throws IOException {
    
    	jgen.writeString(consultant.getIdConsultant().toString());
    }
}