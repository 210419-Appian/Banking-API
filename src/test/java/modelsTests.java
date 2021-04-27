import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import exceptions.models.InvalidNonNullValue;
import exceptions.models.InvalidPrimaryKey;
import exceptions.models.InvalidUniqueValue;
import models.*;

public class modelsTests {
	
	@Test
	public void roleCreateTest() {
		try {
			Role myRole = new Role(1, "Associate");
			assertTrue((myRole.getRoleId() == 1) && (myRole.getRole() == "Associate"));
		}catch(InvalidPrimaryKey e){
			e.printStackTrace();
		}catch(InvalidUniqueValue e) {
			e.printStackTrace();
		}catch(InvalidNonNullValue e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void roleUpdateTest() {
		//TODO: Do this!!!
	}
	
	@Test
	public void roleThrowTest() {
		//TODO: Do this!!!
	}
	
}
