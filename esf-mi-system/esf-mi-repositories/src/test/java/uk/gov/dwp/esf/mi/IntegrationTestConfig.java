package uk.gov.dwp.esf.mi;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.CustomConversions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;

import com.mongodb.Mongo;
import com.mongodb.WriteConcern;

@Configuration
@ComponentScan
@EnableMongoRepositories(basePackages = "uk.gov.dwp.esf")
public class IntegrationTestConfig extends AbstractMongoConfiguration {


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
                new DateReadConverter(),
                new DateWriteConverter()
        ));
	}
	
	@ReadingConverter
	class DateReadConverter implements Converter<String, Date> {
	    @Override
	    public Date convert(String date) {
	    	final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			try {
				return dateFormat.parse(date);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				return null;
			}
	        // Conversion code omitted.
	    }
	}

	@WritingConverter
	class DateWriteConverter implements Converter<Date, String> {

	    @Override
	    public String convert(Date date) {
	    	final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			return dateFormat.format(date);
	        // Conversion code omitted.
	    }
	}

}
