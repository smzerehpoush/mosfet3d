package me.mahdiyar;

public class Format {

    private int noOfSigDigits;
    private double commonFactor;
    private int exponent;
    private boolean rounded;

    public Format(int i, double d) {
        noOfSigDigits = 3;
        commonFactor = 1.0D;
        rounded = false;
        if (i >= 1)
            noOfSigDigits = i;
        if (d != 0.0D)
            commonFactor = d;
    }

    public String formE(double d) {
        rounded = false;
        double d1 = d * commonFactor;
        String s;
        if (d1 > 0.0D)
            s = formPos(d1);
        else if (d1 == 0.0D)
            s = "0";
        else
            s = "-" + formPos(-d1);
        return s;
    }

    private String formPos(double d) {
        if (d < 0.0D)
            return "-" + formPos(-d);
        String s;
        if (d < 1.0D)
            s = formPosSmall(d);
        else if (d == 1.0D)
            s = "1";
        else
            s = formPosLarge(d);
        return s;
    }

    private String formPosSmall(double d) {
        exponent = 0;
        double d1;
        for (d1 = d; d1 < 1.0D; ) {
            d1 *= 10D;
            exponent--;
        }

        return toSigDigits(d1) + "E" + exponent;
    }

    private String toSigDigits(double d) {
        double d1 = Math.pow(10D, (double) noOfSigDigits - 1);
        int i = (int) (d * d1 + 0.5D);
        int j = 1;
        for (int k = 0; k < noOfSigDigits; k++)
            j *= 10;

        if (i == j) {
            exponent++;
            rounded = true;
        }
        StringBuffer stringbuffer = new StringBuffer(String.valueOf(i));
        stringbuffer.insert(1, '.');
        String s = stringbuffer.toString();
        if (rounded)
            return s.substring(0, s.length() - 1);
        else
            return s;
    }

    private String formPosLarge(double d) {
        exponent = 0;
        String s;
        if (d < 10D) {
            s = toSigDigits(d);
            if (rounded)
                s = s + "E" + exponent;
        } else {
            while (d >= 10D) {
                d /= 10D;
                exponent++;
            }
            s = toSigDigits(d) + "E" + exponent;
        }
        if (s.endsWith("E1"))
            s = removeE(s);
        return s;
    }

    private String removeE(String s) {
        if (!s.endsWith("E1"))
            return s;
        String s1 = s.substring(0, s.length() - 2);
        if (noOfSigDigits == 1 && s1.length() == 1)
            return s1 + "0";
        if (noOfSigDigits == 1 && s1.length() == 2) {
            char[] ac = {
                    s1.charAt(0)
            };
            return new String(ac) + "0";
        }
        if (noOfSigDigits == 2 && s1.length() == 3) {
            char[] ac1 = new char[2];
            ac1[0] = s1.charAt(0);
            ac1[1] = s1.charAt(2);
            return new String(ac1);
        }
        if (s1.length() > 2) {
            char[] ac2 = new char[s1.length()];
            ac2[0] = s1.charAt(0);
            ac2[1] = s1.charAt(2);
            ac2[2] = '.';
            for (int i = 3; i < s1.length(); i++)
                ac2[i] = s1.charAt(i);

            return new String(ac2);
        } else {
            System.out.println(" Format.removeE(String) " + s1.length());
            return "000000";
        }
    }
}
