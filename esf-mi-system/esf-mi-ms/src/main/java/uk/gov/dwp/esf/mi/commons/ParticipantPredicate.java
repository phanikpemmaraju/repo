package uk.gov.dwp.esf.mi.commons;

import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.path.PathBuilder;
import com.mysema.query.types.path.StringPath;
import com.mysema.query.types.path.NumberPath;

import java.util.List;
import java.util.Arrays;

import uk.gov.dwp.esf.mi.model.Participant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParticipantPredicate {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());	
	
	private PathBuilder<Participant> participantPath;
	private SearchCriteria criteria;
	private BooleanExpression expression;
	
	public ParticipantPredicate(){
		this.participantPath = new PathBuilder<Participant>(Participant.class,"participant");
	}
		
	public SearchCriteria getCriteria() {
		return criteria;
	}

	public void setCriteria(SearchCriteria criteria) {
		this.criteria = criteria;
	}

	/*
	 * This method creates a Predicate value based on the filter criteria provided.
	 * 
	 */
	public BooleanExpression getPredicate() {
		//PathBuilder<ESFParticipant> participantPath = new PathBuilder<ESFParticipant>(ESFParticipant.class,"participant");
		String criteriaValue = criteria.getValue().toString();

		// Check whether multiple values are provided as part of the filter criteria
		if (checkForMultipleValues(criteriaValue)) {
			final String[] criteriaValues = criteriaValue.split(",");
			final List<String> strings = Arrays.asList(criteriaValues);
			
			// Check whether multiple values provided are numbers
			if (isNumeric(strings.get(0))) {
				final NumberPath<Integer> path = participantPath.getNumber(criteria.getKey(), Integer.class);
				
				strings.forEach(string ->  {					
						int value = Integer.parseInt(string); 
						expression = (expression == null) ? numberExpressions(value, criteria, path) : 
							(expression = 
								(criteria.getOperation().equalsIgnoreCase("!=") || criteria.getOperation().equalsIgnoreCase("<>"))
								? 
										expression.and(numberExpressions(value, criteria, path))
										: expression.or(numberExpressions(value, criteria, path)));
										;} );

				// return number expression
				return expression;
			} else {
				final StringPath path = participantPath.getString(criteria.getKey());		
				//strings.forEach(string -> expression = (expression == null) ? stringExpressions(string, criteria, path) : expression.or(stringExpressions(string, criteria, path)));
				strings.forEach(string -> {
					expression = (expression == null) ? stringExpressions(string, criteria, path) : 
						(expression =
								(criteria.getOperation().equalsIgnoreCase("!=") || criteria.getOperation().equalsIgnoreCase("<>"))
								?
										expression.and(stringExpressions(string, criteria, path))
						 : expression.or(stringExpressions(string, criteria, path)));
					;} );
				// return string expression
				return expression;
			}
		} else {
			if (isNumeric(criteria.getValue().toString())) {
				final NumberPath<Integer> path = participantPath.getNumber(criteria.getKey(), Integer.class);
				int value = Integer.parseInt(criteria.getValue().toString());
				return numberExpressions(value, criteria, path);
			} else {
				final StringPath path = participantPath.getString(criteria.getKey());
				return stringExpressions(criteria.getValue().toString(), criteria, path);
			}
		}
	}

	/*
	 * Check whether given string is a numeric string value
	 */
	public static boolean isNumeric(final String str) {
		try {
			Long.parseLong(str);
		} catch (final NumberFormatException e) {
			return false;
		}
		return true;
	}

	/*
	 * Check whether multiple values does exist
	 */
	public boolean checkForMultipleValues(final String string) {
		return string.contains(",");
	}

	/*
	 * This method returns a BooleanExpression based on the operator provided for a NumberPath
	 * 
	 */
	public BooleanExpression numberExpressions(final int value, final SearchCriteria criteria, final NumberPath<Integer> path) {
		switch(criteria.getOperation()){
		case "=":
			return path.eq(value);
		case ">":
			return path.gt(value);
		case "_gt":
			return path.gt(value);
		case "<":
			return path.lt(value);
		case "_lt":
			return path.lt(value);
		case ">=":
			return path.goe(value);
		case "_gt=":
			return path.goe(value);
		case "<=":
			return path.loe(value);
		case "_lt=":
			return path.loe(value);
		case "!=":
			return path.ne(value);
		case "_in=":
			return path.in(value);
		case "<>":
			return path.ne(value);
		default:
			return null;
		}
	}

	/*
	 * This method returns a BooleanExpression based on the operator provided for a StringPath.
	 * 
	 */
	public BooleanExpression stringExpressions(final String value, final SearchCriteria criteria, final StringPath path) {
		switch(criteria.getOperation()){
		case "=":
			return path.eq(value);
		case "!=":
			return path.ne(value);
		case "<>":
			return path.ne(value);
		case "_like=":
			return path.contains(value);
		case "_in=":
			return path.in(value);
		case "_between=":			
			final String[] dates = value.split(":");
			return path.between(dates[0], dates[1]);
		default:
			return null;
		}
	}

	@Override
	public String toString() {
		return "ParticipantPredicate [participantPath=" + participantPath + ", criteria=" + criteria
				+ ", expression=" + expression + "]";
	}
	

	
}
