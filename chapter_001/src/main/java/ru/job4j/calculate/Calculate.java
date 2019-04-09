package ru.job4j.calculate;

public class Calculate {
	public static void main(String[] args) {
		Calculate calc = new Calculate();
		System.out.println(calc.echo("ahhh"));
	}
	
	public String echo(String value) {
		return String.format("%s %s %s", value, value, value);
	}
	
}