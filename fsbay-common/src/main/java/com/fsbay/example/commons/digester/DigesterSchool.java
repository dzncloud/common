package com.fsbay.example.commons.digester;

import java.io.File;

import org.apache.commons.digester.Digester;

import com.fsbay.example.model.School;
import com.fsbay.example.utils.Utils;

public class DigesterSchool {    
    public static void main(String[] args) throws Exception{    
    	Digester digester = new Digester();    
        digester.addObjectCreate("school", "com.fsbay.example.model.School");    
        digester.addSetProperties("school");    
        digester.addObjectCreate("school/department", "com.fsbay.example.model.Department");    
        digester.addSetProperties("school/department");    
        digester.addSetNext("school/department", "addDepartment");    
        digester.addObjectCreate("school/department/student",    
                "com.fsbay.example.model.Student");    
        digester.addSetNext("school/department/student", "addStudent");    
        digester.addSetProperties("school/department/student");    
        School school = (School)digester.parse(new File(Utils.getCnfPath("/digester/school.xml")));    
        System.out.println(school);
    }
    
    public void other(){
    	/*digester.addSetProperties("root/configs/config", "key", "name");
        digester.addSetProperties("root/configs/config", "value", "value");
        // 定义解析到对应节点（root/configs/config）时，调用本实例的 addAttributeInfo 方法
        digester.addSetNext("root/configs/config", "addAttributeInfo");*/
    }
    
}  