package bases;

class BinaryNumber extends Base {
	BinaryNumber(int initializingValue) {
		int m_valueInDecimal = initializingValue;
		int m_valueInBase = calculateBaseValue(initializingValue);
	}
	
	int calculateBaseValue(int decimalValue) {
		return 0;
	}
}
