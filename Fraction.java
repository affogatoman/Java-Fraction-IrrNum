package math1.cw.hs.kr.math1;

import android.util.Log;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by Administrator on 2016-06-25.
 */
public class Fraction {
    BigInteger num;
    BigInteger denom;

    public static Fraction ONE = new Fraction(BigInteger.ONE, BigInteger.ONE);
    public static Fraction ZERO = new Fraction(BigInteger.ZERO, BigInteger.ONE);

    public Fraction(BigInteger num, BigInteger denom) {
        BigInteger gcd = denom.gcd(num);
        if(denom.compareTo(BigInteger.ZERO) < 0) {
            denom = denom.multiply(new BigInteger("-1"));
            num = num.multiply(new BigInteger("-1"));
        }
        this.num = num.divide(gcd);
        this.denom = denom.divide(gcd);
    }

    public Fraction(String s) {
        if(s.contains("/")) {
            BigInteger num = new BigInteger(s.split("/")[0]);
            BigInteger denom = new BigInteger(s.split("/")[1]);
            BigInteger gcd = denom.gcd(num);
            if (denom.compareTo(BigInteger.ZERO) < 0) {
                denom = denom.multiply(new BigInteger("-1"));
                num = num.multiply(new BigInteger("-1"));
            }
            this.num = num.divide(gcd);
            this.denom = denom.divide(gcd);
        } else {
            this.num = new BigInteger(s);
            this.denom = BigInteger.ONE;
        }
    }

    public Fraction(BigInteger num) {
        this.num = num;
        this.denom = BigInteger.ONE;
    }

    public Fraction(Fraction a, Fraction b) {
        BigInteger denom = a.denom.multiply(b.num);
        BigInteger num = a.num.multiply(b.denom);
        BigInteger gcd = denom.gcd(num);
        if(denom.compareTo(BigInteger.ZERO) < 0) {
            denom = denom.multiply(new BigInteger("-1"));
            num = num.multiply(new BigInteger("-1"));
        }
        this.num = num.divide(gcd);
        this.denom = denom.divide(gcd);
    }

    public String toString() {
        return num.toString() + "/" + String.valueOf(denom);
    }

    public String toMathString() {
        return "{"+num.toString()+(denom.compareTo(BigInteger.ONE) != 0 ? "\\over "+denom.toString() : "")+"}";
    }

    public Fraction inverse() {
        return new Fraction(denom, num);
    }

    public Fraction subtract(Fraction a) {
        return new Fraction(num.multiply(a.denom).subtract(denom.multiply(a.num)), denom.multiply(a.denom));
    }

    public Fraction add(Fraction a) {
        return new Fraction(num.multiply(a.denom).add(denom.multiply(a.num)), denom.multiply(a.denom));
    }

    public Fraction multiply(Fraction a) {
        return new Fraction(num.multiply(a.num), a.denom.multiply(denom));
    }

    public int compareTo(Fraction a) {
        return num.multiply(a.denom).subtract(denom.multiply(a.num)).compareTo(BigInteger.ZERO);
    }

    public Fraction pow(int a) {
        return new Fraction(num.pow(a), denom.pow(a));
    }

    public BigDecimal toBigDecimal() {
        return new BigDecimal(num).divide(new BigDecimal(denom));
    }
}
