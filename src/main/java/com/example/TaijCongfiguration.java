package com.example;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;
@Component
@Data
//@Data : 注解在类上, 为类提供读写属性, 此外还提供了 equals()、hashCode()、toString() 方法
@ConfigurationProperties("taiji")
public class TaijCongfiguration {
    private  boolean enabled;
    private InetAddress remoteAddress;
    private final TJ tj = new TJ();
    @Data
    public static class TJ{
    	  private String username;
    	  private String password;
    	  private List<String> roles=new ArrayList<>(Collections.singleton("USER"));
    }
    
    
}
