package uk.gov.dwp.esf.mi.commons;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import uk.gov.dwp.esf.mi.exceptions.DataException;
import uk.gov.dwp.esf.mi.exceptions.PredicateException;
import uk.gov.dwp.esf.mi.model.Participant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import com.mysema.query.types.expr.BooleanExpression;

@Component
public class PredicatesBuilder{
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());	
		
	/*
	 * This method returns a final BooleanExpression based on the search criteria
	 *  throws NoSuchFieldException 
	 */
	public BooleanExpression createPredicateBuilder(final String filter) throws PredicateException {
		List<DataException> exceptions = new ArrayList<>();
		PredicateException exception = new PredicateException();
		final List<SearchCriteria> params =  new ArrayList<SearchCriteria>();
		// Works fine with the below pattern for data objects too.
		final Pattern pattern = Pattern.compile("(\\w+?)(<|>|=|<=|>=|_like=|_lt|_gt|!=|<>|=|_lt=|_gt=|_in=|_between=)((\\d{4}-[01]\\d-[0-3]\\d|\\w+)[,|:](\\d{4}-[01]\\d-[0-3]\\d|\\w+)*)");
		
		// try with resources
		try (Scanner scanner = new Scanner(filter)) {
			final Class<Participant> participantClass = Participant.class;
			scanner.useDelimiter("&");
			while(scanner.hasNext()){				
				final String criteria = scanner.next();
				final Matcher matcher = pattern.matcher(criteria + ",");
				while (matcher.find()) {
					try{
						participantClass.getDeclaredField(matcher.group(1));
					}catch(NoSuchFieldException ex){
						exceptions.add(new DataException("error.not.found","NoSuchFieldException: " + ex.getMessage()));						
					}
					
					params.add(new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3)));
				}
			}
		}
		
		if(exceptions.size() != 0){
			exception.setExceptions(exceptions);
			throw exception;
		}					
					
		/*if (params.size() == 0) {
			return null;
		}*/

		final List<BooleanExpression> predicates = new ArrayList<BooleanExpression>();
		ParticipantPredicate predicate;
		
		for (SearchCriteria criteria : params) {
			predicate = new ParticipantPredicate();
			predicate.setCriteria(criteria);
			BooleanExpression exp = predicate.getPredicate();
			if (exp != null) {
				predicates.add(exp);
			}
		}
		
		BooleanExpression result = predicates.get(0);
		
		for (int i = 1; i < predicates.size(); i++) {
			result = result.and(predicates.get(i));
		}
		
		//exceptions.clear();
		return result;
	}


}
