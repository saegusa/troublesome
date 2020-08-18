package uno;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Java 14 spec test.
 * 
 * @see https://qiita.com/taumax/items/4208d5cdbb786b76a5ed
 */
@SuppressWarnings("preview")
class MainTest {

	@Test
	void testMain () {
		Main.main(null);
		System.out.println("""
				abc
				test
				アイウエオ
				かきくけこ
				""");
	}

	public static Stream< Object > provideData () {
		return Stream.of("Test", Integer.valueOf(200), Long.valueOf(300));
	}

	/**
	 * JEP 305：Pattern Matching for instanceof (Preview)
	 */
	@ParameterizedTest
	@MethodSource("provideData")
	public void JEP305 ( Object obj ) {

		// これまでの書き方
		if (obj instanceof String) {
			String str = (String) obj;
			System.out.println("No Pattern Matching:" + str);
		}
		else if (obj instanceof Integer) {
			Integer inte = (Integer) obj;
			System.out.println("No Pattern Matching:" + inte);
		}

		// Pattern Matching for instanceof
		if (obj instanceof String str) {
			System.out.println("Pattern Matching:" + str);
		}
		else if (obj instanceof Integer inte) {
			System.out.println("Pattern Matching:" + inte);
		}
	}

}