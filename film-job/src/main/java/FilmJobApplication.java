import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author:Zhang jc
 * @date: 2018/12/6 13:56
 * @description: 定时任务job
 */
@EnableScheduling
@SpringBootApplication(scanBasePackages = {"com.*"})
public class FilmJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilmJobApplication.class, args);
    }
}
