import java.io.Serializable;
import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;


public class c implements Serializable{

	/**
	 * JDBC¸Ä½ø
	 */
	private static final long serialVersionUID = -6699320154318580733L;
	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		RowSetFactory rfc=RowSetProvider.newFactory();
		try(JdbcRowSet sete=rfc.createJdbcRowSet()){
			sete.setUrl("");
			sete.setCommand("");
			sete.execute();
			sete.absolute(1);
			sete.updateString("", "");
			sete.updateRow();
			
		}
		
			
	}
}
