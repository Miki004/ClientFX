package com.example.clientfx.Controllers;

public class SettingDbException extends Exception{
    SettingDbException(){}
    SettingDbException(String str) {
        super(str);
    }
    public static void verificaVuoto(String str) throws SettingDbException {
        if(str.isEmpty()) {
            throw  new SettingDbException(str);
        }
    }
}
