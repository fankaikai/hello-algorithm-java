package com.fkk.code.jz;

/**
 * 替换空格
 */
public class JZ5 {

    public String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            if (temp == ' ') {
                sb.append("%20");
            } else {
                sb.append(temp);
            }
        }
        return sb.toString();
    }
}
