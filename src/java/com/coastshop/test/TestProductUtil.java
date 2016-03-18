/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.coastshop.test;

import com.coastshop.util.ProductUtil2015;

/**
 *
 * @author Coast
 */

    //1341F02021702002
    //1413E17020102003
    //15101AE02001205105    
    //15101AE02001210101    
    //15103AF02010410202
    //15102AE02000702004
public class TestProductUtil {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String sn = "15102AE02000702005";
        boolean flag = ProductUtil2015.checkSNO(sn);
        
        System.out.println(flag);
    }
}
