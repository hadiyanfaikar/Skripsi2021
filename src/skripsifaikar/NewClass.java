
package skripsifaikar;

public class NewClass {

    public static String removeCharAt(String s, int pos) {
        StringBuffer buf = new StringBuffer(s.length() - 1);
        buf.append(s.substring(0, pos)).append(s.substring(pos + 1));
        return buf.toString();
    }
}
