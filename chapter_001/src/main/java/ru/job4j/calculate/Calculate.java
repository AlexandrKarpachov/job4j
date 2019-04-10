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
	public static void main(String[] args) {
		Calculate calc = new Calculate();
		System.out.println(calc.echo("ahhh"));
	}
	
	/**
     * Method echo.
     * @param name Your name.
     * @return Echo plus your name.
     */
    public String echo(String name) {
        return "Echo, echo, echo : " + name;
    }
}