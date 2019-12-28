public class TestType {
    public static void main(String[] args) {
        SomeClass o = new SomeClass();
        o.aBoolean = true;
        o.aByte = 3;
        o.aChar = 'z';
        o.anInt = 32;
        o.aFloat = 0.2f;
        o.aLong = 999L;
        o.aDouble = 4.5;

        System.out.println(o.aBoolean + " " + (o.aByte + o.aChar + o.anInt + o.aFloat + o.aLong + o.aDouble));
    }
}
