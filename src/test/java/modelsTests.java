import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.bank.utils.ConnectionUtil;

public class modelsTests {
	
	@Test
	void connectionUtilTestConnection(){
		boolean success = false;
		
		try (Connection myConnection = ConnectionUtil.getDatabaseConnection()){
			success = true;
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		assertTrue(success);
	}
	
}
