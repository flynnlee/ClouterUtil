import com.clouter.clouterutil.reviver.ValueTimeReviver;

public class Test {
	public static void main(String[] args) throws Exception{
		ValueTimeReviver receiver = new ValueTimeReviver(0, System.currentTimeMillis(), 10, 2000);
		while(true){
			System.out.println(receiver.getValue() + " " + receiver.getNextReceiveCd());
			Thread.sleep(200);
		}
	}
}
