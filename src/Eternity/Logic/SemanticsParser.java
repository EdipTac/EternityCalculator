package Eternity.Logic;

import net.objecthunter.exp4j.function.Function;
import net.objecthunter.exp4j.operator.Operator;

public class SemanticsParser {

    private static EternityEngine engine;

    public SemanticsParser(){
        engine  = new EternityEngine(0.000000001, true);
    }

    public SemanticsParser(double precision){
        engine = new EternityEngine(precision);
    }

    public SemanticsParser(double precision, boolean rads){
        engine = new EternityEngine(precision, rads);
    }

    public void setEngineAngle(boolean value){
        engine.setRads(value);
    }

    public void setEnginePrecision(double value) {engine.setPrecision(value);}

    public int getEnginePrecision(){
        double temp = engine.getPrecision();
        String value = Double.toString(temp);
        if(value.contains("E")){
            String decimals[] = value.split("E-");
            return Integer.parseInt(decimals[1]);
        }
        else{
            String decimals[] = value.split("\\.");
            return decimals[1].length();
        }
    }

    public Operator eFactorial = new Operator("!", 1, true, Operator.PRECEDENCE_POWER + 1) {
        @Override
        public double apply(double... args) {
            final int arg = (int) args[0];
            if((double)arg != args[0]){
                throw new IllegalArgumentException("Operand for factorial must be an integer");
            }
            if(arg < 0){
                throw new IllegalArgumentException("Operand for factorial cannot be less than 0");
            }
            return engine.eFactorial(arg);
        }
    };

    public Operator eExpY = new Operator("^", 2, false, Operator.PRECEDENCE_POWER+1) {
        @Override
        public double apply(double... args) {
            if(args[0]-(int)args[0] == 0 && args[1]-(int)args[1] ==0){
                return engine.eExpY(args[0], (long)args[1], false);
            }
            return engine.eExpY(args[0], args[1]);
        }
    };

    public Function eCos = new Function("Cos", 1){
        @Override
        public double apply(double... args){
            return engine.eCos(args[0]);
        }
    };

    public Function eEulerExp = new Function("EulerExp",1) {
        @Override
        public double apply(double... args) {
            return engine.eEulerExp(args[0]);
        }
    };

    public Function eBaseTenExp = new Function("BaseTenExp",1) {
        @Override
        public double apply(double... args) {
            return engine.eBaseTenExp(args[0]);
        }
    };

    public Function eLog = new Function("Log", 1) {
        @Override
        public double apply(double... args) {
            return engine.eLog(args[0]);
        }
    };

    public Function eNaturalLog = new Function("Ln", 1) {
        @Override
        public double apply(double... args) {
            return engine.eLn(args[0]);
        }
    };

    public String preFormatInput(String input){
        String formatted = input;
        formatted = formatted.replace("cos", "Cos");
        formatted = formatted.replace("ln", "Ln");
        formatted = formatted.replace("e^","EulerExp");
        formatted = formatted.replace("log", "Log");
        formatted = formatted.replace("10^", "BaseTenExp");
        return formatted;
    }
}
