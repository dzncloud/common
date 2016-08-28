package com.fsbay.example.commons.digester;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.digester.Digester;

import com.fsbay.example.model.Child;
import com.fsbay.example.model.Parent;
 
public class DigesterRoot {
      
       /**
        * @param args
        */
       public static void main(String[] args) {
              Digester digester=new Digester();
              //digester.setValidating(false);
              digester.addObjectCreate("root",ArrayList.class);//create an ArrayList when got a ‘/root’ element
              digester.addObjectCreate("root/parent", Parent.class);
              digester.addObjectCreate("root/parent/child", Child.class);
             
              digester.addSetProperties("root/parent", "pname","name");
              digester.addSetProperties("root/parent/child","cname","name");//set propertiy:cname to Child.name
              digester.addBeanPropertySetter("root/parent/child","value");//set node value to Child.value
             
              digester.addSetNext("root/parent", "add");//use add() method of List
              digester.addSetNext("root/parent/child","addChild");
             
              try {
                     List<Parent> parents=(ArrayList<Parent>) digester.parse(new File("D:/my_doc/github/common/apache-common/src/main/resources/digester/NewFile.xml"));
                     System.out.println(parents.get(0).getChilds().get(0).getValue());
              } catch (Exception e) {
                     e.printStackTrace();
              }
       }
 
}