import com.writty.ext.Funcs;

public class EmojiTest {

	public static void main(String[] args) {
		String content = "## aaaa"
				+ ""
				+ ":heart:";
		String next = Funcs.emoji(content);
		System.out.println(next);
		
	}

}
