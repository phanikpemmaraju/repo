package uk.gov.dwp.esf.mi.common;

/*
 *  @Author : Phani Krishna
 *  @Description : A generic class with multiple interface definitions for displaying
 *  			   customized information of various objects to the user based
 *  			   on the controller methods.
 *  @Version : 1.0
 * 
 */

public class View {
	
	public interface Summary{}
	public interface SummaryWithBasicContracts extends Summary {}
	public interface SummaryWithFullContracts extends SummaryWithBasicContracts {}
	public interface SummaryWithBasicParticipants extends Summary {}
	public interface SummaryWithFullParticipants extends SummaryWithBasicParticipants {}
	public interface Other {}

}
