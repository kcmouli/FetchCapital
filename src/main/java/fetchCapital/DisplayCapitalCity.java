package fetchCapital;

import java.util.Scanner;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

public class DisplayCapitalCity {
	public String getCapital(String input){
		String capitalCity=null, uri=null;
		try {
			Client client = ClientBuilder.newClient();
			if(input.matches("[0-9]+") && input.length()>0){
				uri = "callingcode/"+input;
			}else if(input.matches("[a-zA-Z]+") && input.length()>0){
				uri = "capital/"+input;
			}else
				return "Please enter either name or code.";

			WebTarget target = client.target("https://restcountries.eu/rest/v2/"+uri);
			Response response = target.request(MediaType.APPLICATION_JSON_TYPE).get();
			if(response.getStatus() == 404){
				return "Didn't find any capital matching with given input.Try again.";
			}
			String strResp =  response.readEntity(String.class);
			JSONArray jsonArr = new JSONArray(strResp);
			JSONObject jsonObj =jsonArr.getJSONObject(0);
			//			System.out.println(jsonObj.getString("capital"));
			capitalCity =  jsonObj.getString("capital");
		} catch (Exception e) {
			System.out.println("Exception in getCapital:- " + e);
		}
		return capitalCity;
	}

	public static void main(String[] args) {
		DisplayCapitalCity dcc = new DisplayCapitalCity();
		String userOpinion;
		do{
			System.out.println("Enter name or code :");
			Scanner sc= new Scanner(System.in);
			String input = sc.next();
			System.out.println("Matching capital city for given input is : "+ dcc.getCapital(input));
			System.out.println("Do you want to continue - yes:");
			userOpinion = sc.next();
		}while(userOpinion.equalsIgnoreCase("yes"));
	}

}
