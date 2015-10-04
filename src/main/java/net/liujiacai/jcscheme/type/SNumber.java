package net.liujiacai.jcscheme.type;


public class SNumber extends SObject{

	private int value;

	public int getValue() {
		return value;
	}
	public SNumber(int value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return String.valueOf(value);
	}
	

	// 以下为该类型提供的内置函数
	public static SObject add(SObject... args) {
		int sum = 0;
		for (SObject a : args) {
			sum += ((SNumber) a).getValue();
		}
		return new SNumber(sum);
	}

	public static SObject sub(SObject... args) {
		int total = ((SNumber) args[0]).getValue();
		for (int i = 1; i < args.length; i++) {
			total -= ((SNumber) args[i]).getValue();
		}
		return new SNumber(total);
	}

	public static SObject mul(SObject... args) {
		int product = 1;
		for (SObject a : args) {
			product *= ((SNumber) a).getValue();
		}
		return new SNumber(product);
	}

	public static SObject div(SObject... args) {
		int total = ((SNumber) args[0]).getValue();
		for (int i = 1; i < args.length; i++) {
			total /= ((SNumber) args[i]).getValue();
		}
		return new SNumber(total);
	}

}
