package uk.gov.dwp.esf.mi;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { IntegrationTestConfig.class })
public abstract class AbstractIntegrationTest {
	
	public final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	@Autowired
	Mongo mongo;

	@SuppressWarnings("deprecation")
	@Before
	public void setUp() throws ParseException{
		DB database = mongo.getDB("esf-mi-integration-test");

		// Participants
		
		DBCollection participants = database.getCollection("participants");
		participants.drop();
				
		DBObject mark = new BasicDBObject();
		mark.put("providerId", 1111111);
		mark.put("nino", "SJ196058B");
		mark.put("dob", dateFormat.parse("1991-12-16"));
		mark.put("contractId", 4001234);
		
		DBObject steve = new BasicDBObject();
		steve.put("providerId", 1111113);
		steve.put("nino", "CD756309C");
		steve.put("dob", dateFormat.parse("1977-07-27"));
		steve.put("contractId", 4001235);
		
		DBObject davies = new BasicDBObject();
		davies.put("providerId", 1111114);
		davies.put("nino", "AB234071A");
		davies.put("dob", dateFormat.parse("1982-01-22"));
		davies.put("contractId", 4001236);
		
		DBObject cook = new BasicDBObject("_id",new ObjectId("56e14abf710856264052cc14"));
		cook.put("providerId", 1111112);
		cook.put("nino", "EF984361C");
		cook.put("dob", dateFormat.parse("1985-03-22"));
		cook.put("contractId", 4009832);	
		cook.put("providerRef", "1111114");
						
		participants.insert(mark,steve,davies,cook);

	}

}
