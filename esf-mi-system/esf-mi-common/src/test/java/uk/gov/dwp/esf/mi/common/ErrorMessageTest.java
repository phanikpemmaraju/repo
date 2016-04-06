package uk.gov.dwp.esf.mi.common;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.junit.Test;

public class ErrorMessageTest {
	
	@Test
    public void shouldReturnErrorBuilder() {
		assertThat(new ErrorMessage.ErrorBuilder(null, null, null,null),isA(ErrorMessage.ErrorBuilder.class));
    }
	
	@Test
    public void shouldBuildADefaultError() {
        assertThat(new ErrorMessage.ErrorBuilder(null, null, null,null).build(), isA(ErrorMessage.class));
    }
	
	@Test
    public void shouldBuildAnErrorMessage() {
		ErrorMessage error = new ErrorMessage.ErrorBuilder("400","Invalid NI","Bad Request","https://confluence.dwp.gov.uk/participant/errorcodes/400")
                .build();

        assertThat(error.getCode(), is("400"));
        assertThat(error.getMessage(), is("Invalid NI"));
        assertThat(error.getDescription(), is("Bad Request"));
        assertThat(error.getUrl(), is("https://confluence.dwp.gov.uk/participant/errorcodes/400"));
                
        final String value = "ErrorMessage [code=400, message=Invalid NI, description=Bad Request, url=https://confluence.dwp.gov.uk/participant/errorcodes/400]";
        assertThat(error.toString(), is(value));
    }

}
