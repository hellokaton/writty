import java.util.Arrays;
import java.util.UUID;

import com.writty.kit.Utils;

public class TestMain {

	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString().replace("-", ""));
		
		System.out.println(Arrays.toString(Utils.randomCommon(1, 9, 8)));
	}

}
