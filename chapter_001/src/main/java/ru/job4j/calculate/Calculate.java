package ru.job4j.calculate;
/**
*Class Calculate solve of the exercize of the part 001 of lesson1
*@author Aleksandr Karpachov
*@since 09.04.2019
*/


public class Calculate {
	/**
	*Constructor, output a string into the console.
	*@param args - arg.
	*/
	public static void main(final String[] args) {
		Calculate calc = new Calculate();
		System.out.println(calc.echo("ahhh"));
	}
	
	/** Method of testing.
	*@param value is a string for output into the console.
	*@return String value.
	*/
	public String echo(final String value) {
		if(value != null) {
			value  = String.format("%s value =", value);
		} else {
			value = "value = null";
		}
		return String.format("%s %s %s", value, value, value);
	}
}