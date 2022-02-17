package hr.atos.praksa.hrvojeskrbina.zadatak14;

public class Integrator implements FunctionIntegration {

	public String type = "";
	
	public void setType(String newType) { this.type = newType; }
	public String getType() { return this.type; }

	public double function(double x,double A, double B) {
		double tan = Math.sin(x) / Math.cos(x),
			cot = Math.cos(x) / Math.sin(x);
		switch(this.type) {
			case "sin": return A * Math.sin(x) + B;
			case "cos": return A * Math.cos(x) + B;
			case "tan": return A * tan + B;
			case "cot": return A * cot + B;
			default: return -1;
		}
	}

	public double integrate(double[] variables, int N) {
		double h, summation, x;
		int i;
		h = (variables[3] - variables[2]) / N;
		summation = 0.5 * (function(variables[2], variables[0], variables[1]) + function(variables[3], variables[0], variables[1]));
		for (i = 1; i < N; i++) {
			x = variables[2] + h * i;
			summation = summation + function(x, variables[0], variables[1]);
		}	
		return summation * h;
	}

}
