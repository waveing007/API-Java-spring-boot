package Baiwa.TestAPI;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestApiApplication{

	public static void main(String[] args) {
		SpringApplication.run(TestApiApplication.class, args);
	}
	
	@PostConstruct
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Bangkok"));
//        ApplicationCache.loadCache(listMasterCache);
	}

	
}
