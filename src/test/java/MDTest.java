import com.writty.ext.markdown.Processor;

public class MDTest {

	public static void main(String[] args) {
		String processed = Processor.process("## Hello World\n [baidu](http://www.baidu.com)");
		System.out.println(processed);
	}

}
