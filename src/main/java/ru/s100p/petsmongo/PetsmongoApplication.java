package ru.s100p.petsmongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


//TODO добавить валидацию
//TODO добавить уникальное поле в модель и проверки на "такой же" объект
//TODO доделать функционал по гайду и схеме


@EnableFeignClients //С помощью этой аннотации мы включаем сканирование компонентов для интерфейсов, которые заявляют, что они являются клиентами Feign (см https://for-each.dev/lessons/b/-spring-cloud-openfeign)
@SpringBootApplication
public class PetsmongoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetsmongoApplication.class, args);
	}

}
