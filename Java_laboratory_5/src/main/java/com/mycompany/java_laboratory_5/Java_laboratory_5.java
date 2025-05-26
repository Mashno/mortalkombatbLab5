/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.java_laboratory_5;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;



/**
 *
 * @author Владислав
 */
public class Java_laboratory_5 {

    public static void main(String[] args) {
        
        try {
        System.setOut(new PrintStream(System.out, true, "UTF-8"));
        System.setErr(new PrintStream(System.err, true, "UTF-8"));
        GUI gui = new GUI();
        gui.setVisible(true);
    } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    }
    }
    }

