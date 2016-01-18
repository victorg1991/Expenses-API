package serializer;

import models.CashFlow;
import models.Profit;
import models.Spending;
import play.libs.Json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JsonHelper {
	
	public static <T extends CashFlow> JsonNode getJsonNode(T cashFlow){
		
		ObjectNode node = (ObjectNode) Json.toJson(cashFlow);
		
		if (cashFlow instanceof Profit){
			node.put("idAdvisedUser", ((Profit) cashFlow).getUser().getIdAdvisedUser());
		}
		
		if (cashFlow instanceof Spending){
			node.put("idAdvisedUser", ((Spending) cashFlow).getUser().getIdAdvisedUser());
		}
		
		return node;
	}

}
