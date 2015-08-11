 import dk.ange.octave.OctaveEngine;
 import dk.ange.octave.OctaveEngineFactory;
 import dk.ange.octave.type.OctaveDouble;
 import dk.ange.octave.type.OctaveString;
 import dk.ange.octave.type.Octave;

 import org.apache.commons.math3.linear.*;
 import junit.framework.TestCase;
 import java.io.*;

public class CallOctave extends TestCase{

    public static void main(String[] args) { 
		CallOctave octave_examples = new CallOctave();
    }


    private CallOctave() {

	OctaveEngineFactory factory = new OctaveEngineFactory();
	factory.setOctaveProgram(new File("C:\\Octave\\Octave-4.0.0\\bin\\octave-cli"));
	OctaveEngine octave = factory.getScriptEngine();

	// 1. example
	//OctaveDouble matA = new OctaveDouble(new double[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15}, 3, 5);  // 3 rows, 5 cols
	//octave.put("a", matA);
	//octave.eval("a");

	String matB = "b = [1,2,3; 4,5,6; 7,8,9]";
	octave.eval(matB);

	octave.eval("x = b(1,:)");
	OctaveDouble varX = (OctaveDouble) octave.get("x");
	//System.out.println("Result: "+varX.get(1)) +" "+varX.get(2)+" "+varX.get(3));
	System.out.println("x=" + new ArrayRealVector(varX.getData()));
	System.out.println();

	// 2. example
	//OctaveDouble a = new OctaveDouble(new double[] { 1, 2, 3, 4 }, 2, 2);
	//octave.put("a", a);
	octave.eval("a= [1,2; 3,4]");
	String func = "" //
	 + "function res = my_func(a)\n" //
	 + " res = 2 * a;\n" //
	 + "endfunction\n" //
	 + "";
	octave.eval(func);
	octave.eval("b = my_func(a);");
	OctaveDouble b = octave.get(OctaveDouble.class, "b");
	System.out.println("b=" + new ArrayRealVector(b.getData()));
	System.out.println();
/*
	// 3. example
	octave.put("fun", new OctaveString("sqrt(1-t**2)"));
	octave.put("t1", Octave.scalar(0));
	octave.put("t2", Octave.scalar(1));
	//octave.eval("result3 = lsode(fun, 0, [t1 t2])(2);");  //use as anonymous function @(x, t)  does not work here,  example has warnings
	octave.eval("result3 = lsode(@(x, t) sqrt(1-t**2), 0, [t1 t2])(2);");
	OctaveDouble result3 = octave.get(OctaveDouble.class, "result3");

	double integral = result3.get(1);
	assertEquals(Math.PI / 4, integral, 1e-5);
	System.out.println("integral="+integral);
	System.out.println();
*/
	// 4. example
	octave.eval("x = 0:0.01:1;");
	octave.eval("t = lsode(@(x, t) x**2+t**2, 0, x);");

	octave.eval("plot(x,t)");
	octave.eval("drawnow()");
		try {
			System.out.println("hit return to resume");
        	System.in.read();
    	} catch (IOException e) {
        	e.printStackTrace();
			octave.close();
    	}

	OctaveDouble t = octave.get(OctaveDouble.class, "t");

	double[] result4 = t.getData();
	System.out.println("result4= " + new ArrayRealVector(result4));

	octave.close();
    }//CallOctave constructor

}//CallOctave class

