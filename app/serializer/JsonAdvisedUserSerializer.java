package serializer;

import java.io.IOException;

import models.AdvisedUser;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JsonAdvisedUserSerializer extends JsonSerializer<AdvisedUser> {

    @Override
    public void serialize(
    		AdvisedUser user, JsonGenerator jgen, SerializerProvider provider) throws IOException {
    
    	jgen.writeString(user.getIdAdvisedUser().toString());
    }
}