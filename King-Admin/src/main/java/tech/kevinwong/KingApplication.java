package tech.kevinwong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author Kevin<EastascendWang@gmail.com>
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class KingApplication
{
    public static void main(String[] args)
    {
        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(KingApplication.class, args);
        System.out.println("Run Success!");
    }
}
