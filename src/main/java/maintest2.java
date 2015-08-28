import java.util.ArrayList;

import controller.cloud.CloudChecker;
import controller.cloud.CloudManager;
import enums.Property;

public class maintest2 {

	public static void main(String[] args) {
		System.out.println(CloudChecker.getCloudAttributes("heroku"));
		ArrayList<Property> properties = new ArrayList<Property>();
		properties.add(Property.ENCRYPTED);
		properties.add(Property.FAST);
		System.out.println(CloudChecker.cloudScore("heroku", properties));
		CloudManager man = new CloudManager();

	}

}
