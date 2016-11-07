import com.clouter.clouterutil.StringUtil;
import com.clouter.clouterutil.json.JsonFormater;
import com.clouter.clouterutil.reviver.ValueFloatTimeReviver;
import com.clouter.clouterutil.reviver.ValueTimeReviver;

public class Test {
	public static void main(String[] args) throws Exception{
		System.out.println(StringUtil.isSuffix("abcdef", "f"));
		System.out.println(StringUtil.isPrefix("abcdef", "x"));
	}
}
