package ru.job4j.calc;

import ru.job4j.calculate.Calculator;

import java.util.List;

public class EngineerOperations extends Operations {

	public EngineerOperations(Calculator calculator) {
		super(calculator);
		Operation cos = new Cos();
		Operation sin = new Sin();
		Operation pow = new Pov();
		Operation sqrt = new Sqrt();
		super.operationsList.put(cos.getMark(), cos);
		super.operationsList.put(sin.getMark(), sin);
		super.operationsList.put(pow.getMark(), pow);
		super.operationsList.put(sqrt.getMark(), sqrt);
	}

	class Cos extends Operation {

		public Cos() {
			super(1, "cos");
		}

		@Override
		public double calculate(List<Double> args) {
			return Math.cos(args.get(0));
		}
	}

	class Sin extends Operation {

		public Sin() {
			super(1, "sin");
		}

		@Override
		public double calculate(List<Double> args) {
			return Math.sin(args.get(0));
		}
	}

	class Sqrt extends Operation {

		public Sqrt() {
			super(1, "sqrt");
		}

		@Override
		public double calculate(List<Double> args) {
			return Math.sqrt(args.get(0));
		}
	}

	class Pov extends Operation {

		public Pov() {
			super(2, "pov");
		}

		@Override
		public double calculate(List<Double> args) {
			return Math.pow(args.get(0), args.get(1));
		}
	}
}
