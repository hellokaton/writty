import blade.kit.http.HttpRequest;
import blade.kit.json.JSONArray;
import blade.kit.json.JSONObject;

public class EmailTest3 {

	public static void main(String[] args) {
		final String url = "http://api.sendcloud.net/apiv2/mail/sendtemplate";
	    final String apiUser = "";
	    final String apiKey = "";

	    JSONObject x_smtpapi = new JSONObject();
	    x_smtpapi.put("to", new JSONArray().add("921293209@qq.com"));
	    JSONObject sub = new JSONObject();
	    sub.put("%username%", new JSONArray().add("biezhi"));
	    sub.put("%url%", new JSONArray().add("http://java-china.org"));
	    x_smtpapi.put("sub", sub);
	    
	    System.out.println(x_smtpapi.toString());
	    
	    String body = HttpRequest.post(url, true, 
	    		"apiUser", apiUser,
	    		"apiKey", apiKey,
	    		"templateInvokeName", "java_china_signup",
//	    		"from", "support@java-china.org",
	    		"fromName", "Java中国",
	    		"xsmtpapi", x_smtpapi.toString(),
	    		"subject", "Java中国账户激活").body();
	    System.out.println(body);
	}

}
