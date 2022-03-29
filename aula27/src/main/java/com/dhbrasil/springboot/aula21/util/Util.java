package com.dhbrasil.springboot.aula21.util;

import java.sql.Timestamp;
import java.util.Date;

public class Util {
    public static Timestamp dataToTimestamp(Date date){
        Timestamp timestamp = new Timestamp(date.getTime());
        return timestamp;
    }
}
