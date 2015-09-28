package com.hisense.hitv.test;

import com.hisense.hitv.EncryptUtils;

public class TestDes {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(EncryptUtils.DESEncrypt("ecf8427e5d933e61", "", 64));
        System.out.println(EncryptUtils.DESDecrypt("ecf8427e5d933e61", ""));
    }

}
