package milkman.plugin.test.domain;

import static milkman.domain.ResponseContainer.StyledText;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;
import milkman.domain.ResponseAspect;
import reactor.core.publisher.Flux;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestResultAspect implements ResponseAspect {

	private Flux<TestResultEvent> results;

	@Override
	public String getName() {
		return "Results";
	}



	public enum TestResultState {
		STARTED, SUCCEEDED, FAILED, EXCEPTION, IGNORED, SKIPPED
	}

	@Value
	public static class TestResultEvent {
		String requestId;
		String requestName;
		TestResultState resultState;
		Map<String, StyledText> details;
	}


}
