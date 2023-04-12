package com.digdes.school;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String... args){
        JavaSchoolStarter starter = new JavaSchoolStarter();
        try {
            List<Map<String,Object>> result = starter.execute
                    ("INSErT VALUES 'lastName' = 'fffff8f' , 'id'=30, 'age'=45, 'active'=true");
            List<Map<String,Object>> result2 = starter.execute
                    ("INSErT VALUES 'lastName' = 'fffff8f' , 'id'=30, 'age'=45, 'active'=false");
            List<Map<String,Object>> result22 = starter.execute
                    ("INSErT VALUES 'lastName' = 'fff3fff' , 'id'=3101, 'age'=45, 'active'=true");
            List<Map<String,Object>> result4 = starter.execute
                    ("INSErT VALUES 'lastName' = 'ff1fq111fff' , 'id'=1, 'age'=45, 'active'=true");
            List<Map<String,Object>> result55 = starter.execute
                    ("INSErT VALUES 'lastName' = 'ff1fqfff' , 'id'=1, 'age'=450, 'active'=true");
            List<Map<String,Object>> result5 = starter.execute
                    ("INSErT VALUES 'lastName' = 'ff4ffff' , 'id'=30, 'age'=45, 'active'=true");
            List<Map<String,Object>> result3 = starter.execute
                    ("SELECT WHERE 'lastName' like '%Q%'");
            System.out.println(result3);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
