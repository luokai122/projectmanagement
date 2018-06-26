package cn.toprs.projectmanagenment.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * @author StrangeLuo
 */
public class BCryptUtil {

    public static String bCrypt(String passworod){
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        return encode.encode(passworod);
    }

}
