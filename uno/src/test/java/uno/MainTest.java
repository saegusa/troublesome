package uno;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

/**
 * Java 14 spec test.
 * 
 * @see <a href=
 *      "https://qiita.com/taumax/items/4208d5cdbb786b76a5ed">article</a>
 */
@SuppressWarnings("preview")
class MainTest {
	
	@Test
	void testMain () {
		Main.main(null);
	}
	
	public static Stream< Object > DataSourceJEP305 () {
		return Stream.of("Test", Integer.valueOf(200), Long.valueOf(300));
	}
	
	/**
	 * JEP 305：Pattern Matching for instanceof (Preview)
	 */
	@ParameterizedTest
	@MethodSource("DataSourceJEP305")
	public void JEP305 ( Object obj ) {
		Object[] actual = new Object[] {null,null};
		Object[] expected = new Object[] {null,null};
		// これまでの書き方
		if (obj instanceof String) {
			String str = (String) obj;
			System.out.println("No Pattern Matching:" + str);
			expected[0] = str;
		}
		else if (obj instanceof Integer) {
			Integer inte = (Integer) obj;
			System.out.println("No Pattern Matching:" + inte);
			expected[1] = inte;
		}
		
		// Pattern Matching for instanceof
		if (obj instanceof String str) {
			System.out.println("Pattern Matching:" + str);
			actual[0] = str;
		}
		else if (obj instanceof Integer inte) {
			System.out.println("Pattern Matching:" + inte);
			actual[1] = inte;
		}
		
		assertEquals(expected[0], actual[0]);
		assertEquals(expected[1], actual[1]);
	}
	
	private enum Week {
		SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
	}
	
	/**
	 * JEP 361：Switch Expressions (Standard)
	 * 
	 * @param day
	 */
	@ParameterizedTest
	@EnumSource(Week.class)
	public void JEP361 ( Week day ) {
		// これまでのswitch文
		System.out.print("old:");
		int value = -1;
		switch (day) {
			case MONDAY, FRIDAY, SUNDAY:
				System.out.print(6);
				value = 6;
				break;
			case TUESDAY:
				System.out.print(7);
				value = 7;
				break;
			case THURSDAY, SATURDAY:
				System.out.print(8);
				value = 8;
				break;
			case WEDNESDAY:
				System.out.print(9);
				value = 9;
				break;
		}
		System.out.println(value);
		
		// 新しいswitch文
		System.out.print("new:");
		switch (day) {
			case MONDAY, FRIDAY, SUNDAY -> System.out.print(6);
			case TUESDAY -> System.out.print(7);
			case THURSDAY, SATURDAY -> System.out.print(8);
			case WEDNESDAY -> System.out.print(9);
		}
		value = switch (day) {
			case MONDAY, FRIDAY, SUNDAY -> 6;
			case TUESDAY -> 7;
			case THURSDAY, SATURDAY -> 8;
			case WEDNESDAY -> 9;
		};
		System.out.println(value);
	}
	
	/**
	 * JEP 368：Text Blocks (Second Preview)
	 */
	@Test
	public void JEP368 () {
		// これまでは改行をエスケープする必要があった。
		String str1 = "I have a pen.\n" + "I don't have a pen.";
		System.out.println(str1);
		
		// エスケープしなくても改行してくれる。
		String str2 = """
					  I have a pen.
					  I don't have a pen.
					  """;
		System.out.println(str2);
		
		// 改行したくない場合は「\」を付ける。
		String str3 = """
					  I have a pen.\
					  I don't have a pen.\
					  """;
		System.out.println(str3);
		
		// 文字列に変数を埋め込む場合
		String str4 = String.format("""
									Hello %s.
									""", "taumax");
		System.out.println(str4);
	}
}