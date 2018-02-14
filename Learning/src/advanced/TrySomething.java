package advanced;

public class TrySomething {
	private static void somethingDangerous() {
		throw new Error("oops");
	}
	public static void main(String[] args) {
		try {
			somethingDangerous();
		} catch(Throwable throwable) {
			Throwable cause = throwable.getCause();
			if (cause != null) {
				throwable = cause;
			}
		}
	}
}
