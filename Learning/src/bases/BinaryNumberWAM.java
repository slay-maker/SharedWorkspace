package bases;

public class BinaryNumberWAM {
	private int m_valueInDecimal;
	private int m_valueInBase;
	public BinaryNumberWAM(int initializingValue) {
		m_valueInDecimal = initializingValue;
		m_valueInBase = calculateBaseValue(initializingValue);
	}
	
	private int calculateBaseValue(int decimalValue) {
		return 0;
	}
	
	public int getValueInBase() {
		return m_valueInBase;
	}
}
