package serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonFloatSerializer extends JsonSerializer<Float> {

	    @Override
	    public void serialize(
	            Float quantity, JsonGenerator jgen, SerializerProvider provider) throws IOException {

	        String formattedFloat = String.format("%0.3f", quantity);
	        jgen.writeString(formattedFloat);
	    }
}
