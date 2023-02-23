/**
 * 
 */
package TDD;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * @author SWR
 *
 */
class TDD_RegTest {

    @DisplayName("empty")
	@Test
	void test1() {
    	TDD_Regi password1=new TDD_Regi();
//    	System.out.println(" "+" "+"1234"+"swr");
		password1.checkPassword(" "," ","1234","swr");
	}

    @DisplayName("comma")
	@Test
	void test2() {
    	TDD_Regi password2=new TDD_Regi();
    	password2.checkPassword(",",",","1234","swr");
	}

    @DisplayName("Not same password")
	@Test
	void test3() {
    	TDD_Regi password3=new TDD_Regi();
		password3.checkPassword("123","456","1234","swr");
	}
    
    @DisplayName("empty in name")
	@Test
	void test4() {
    	TDD_Regi password4=new TDD_Regi();
		password4.checkPassword("123","123","1234","s r");
	}
    
    @DisplayName("comma in telephone number")
	@Test
	void test5() {
    	TDD_Regi password5=new TDD_Regi();
		password5.checkPassword("123","123","12,34","swr");
	}
    
    @DisplayName("Perfect")
	@Test
	void test6() {
    	TDD_Regi password5=new TDD_Regi();
		password5.checkPassword("1234","1234","1364104","swr");
	}
}
