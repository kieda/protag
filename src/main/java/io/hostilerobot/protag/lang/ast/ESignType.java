package io.hostilerobot.protag.lang.ast;

public enum ESignType {
    PLUS(false),
    MINUS(true){
        @Override
        public double negate(double in) {
            return -in;
        }
        @Override
        public int negate(int in) {
            return  -in;
        }
    },
    NONE(false);

    private final boolean isNegative;
    ESignType(boolean isNegative) {
        this.isNegative = isNegative;
    }
    public boolean isNegative() {
        return isNegative;
    }

    public double negate(double in) {
        return in;
    }
    public int negate(int in) {
        return in;
    }
}
