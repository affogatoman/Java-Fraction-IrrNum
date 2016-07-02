package math1.cw.hs.kr.math1;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;

/**
 * Created by Administrator on 2016-06-25.
 */
public class CMath {
    public static IrrNum sqrt(BigInteger qNum) {
        HashMap<BigInteger, BigInteger> map = new HashMap<>();

        BigInteger j;
        if(qNum.compareTo(new BigInteger("2")) == -1)
            return new IrrNum(Fraction.ONE, qNum);
        do {
            j = new BigInteger("2");
            while(qNum.remainder(j).compareTo(BigInteger.ZERO) != 0)
                j = j.add(BigInteger.ONE);
            map.put(j, map.get(j) == null ? BigInteger.ONE : map.get(j).add(BigInteger.ONE));
            qNum = qNum.divide(j);
        } while(qNum.compareTo(BigInteger.ONE) != 0);

        Fraction out = Fraction.ONE;
        BigInteger left = BigInteger.ONE;
        for(BigInteger i : map.keySet()) {
            if(map.get(i).remainder(new BigInteger("2")).compareTo(BigInteger.ZERO) == 0) {
                out = out.multiply(new Fraction(i.pow(map.get(i).divide(new BigInteger("2")).intValue())));
            } else {
                out = out.multiply(new Fraction(i.pow(map.get(i).subtract(BigInteger.ONE).divide(new BigInteger("2")).intValue())));
                left = left.multiply(i);
            }
        }
        return new IrrNum(out, left);
    }

    public static IrrNum sqrt(Fraction f) {
        BigInteger ab = f.num.multiply(f.denom);
        IrrNum result = CMath.sqrt(ab);
        result.out = result.out.multiply(new Fraction(BigInteger.ONE, f.denom));
        return result;
    }

    public static Fraction toFraction(BigDecimal d) {
        String s = d.toPlainString();
        if(!s.contains("."))
            return new Fraction(d.toBigInteger(), BigInteger.ONE);
        int digitsDec = s.length() - 1 - s.indexOf('.');

        return new Fraction(new BigInteger(s.replace(".", "")), BigInteger.TEN.pow(digitsDec));
    }
}
