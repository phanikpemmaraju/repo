package uk.gov.dwp.esf.mi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.CustomConversions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import com.mongodb.Mongo;
import com.mongodb.WriteConcern;

@Configuration
@ComponentScan
@EnableMongoRepositories(basePackages = "uk.gov.dwp.esf")
public class ApplicationConfig extends AbstractMongoConfiguration {


	@Override
	protected String getDatabaseName() {
		// TODO Auto-generated method stub
		return "esf-mi-integration-test";
	}

	@SuppressWarnings("deprecation")
	@Override
	@Bean
	public Mongo mongo() throws Exception {
		// TODO Auto-generated method stub
		Mongo mongo = new Mongo();
		mongo.setWriteConcern(WriteConcern.SAFE);
		return mongo;
	}

	@Override
	public CustomConversions customConversions() {
		return new CustomConversions(Arrays.asList(
                new LocalDateReadConverter(),
                new LocalDateWriteConverter()
        ));
	}
	
	class LocalDateReadConverter implements Converter<String, LocalDate> {
	    @Override
	    public LocalDate convert(String date) {
	    	final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			return LocalDate.parse(date, formatter);
	        // Conversion code omitted.
	    }
	}

	class LocalDateWriteConverter implements Converter<LocalDate, String> {

	    @Override
	    public String convert(LocalDate localDate) {
			return localDate.toString();
	        // Conversion code omitted.
	    }
	}

}
