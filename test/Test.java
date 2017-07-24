import org.apache.velocity.util.StringUtils;

public class Test {
	public static void main(String[] args) throws Exception{
		long m1 = System.currentTimeMillis();
		for(int i = 0; i < 1000; i++){
			StringUtils.split("aa,aa", ",");
		}
		long m2 = System.currentTimeMillis();
		System.out.println(m2 - m1);
	}
}
