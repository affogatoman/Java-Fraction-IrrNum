import java.math.BigInteger;

/**
 * Created by Administrator on 2016-06-25.
 */
public class IrrNum {
    public Fraction out;
    public BigInteger in;

    public IrrNum(Fraction out, BigInteger in) {
        this.out = out;
        this.in = in;
    }

    public IrrNum multiply(IrrNum a) {
        BigInteger bd = a.in.multiply(in);
        IrrNum result = CMath.sqrt(bd);
        result.out = result.out.multiply(a.out);
        return result;
    }

    public String toMathString() {
        String result = "{"+(out.num.compareTo(BigInteger.ONE) != 0
        ? (out.num.compareTo(new BigInteger("-1")) == 0 ? "-" : out.num.toString())
        : ""
        )+
        (in.compareTo(BigInteger.ONE) != 0
        ? "\\sqrt{" + in.toString() + "}"
        : "")+
        (out.denom.compareTo(BigInteger.ONE) != 0
        ? "\\over "+out.denom.toString()
        : "")+"}";
        return result.equals("{}") ? "1" : result;
    }
}
