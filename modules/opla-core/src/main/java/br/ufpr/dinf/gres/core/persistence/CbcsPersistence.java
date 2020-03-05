package br.ufpr.dinf.gres.core.persistence;

import br.ufpr.dinf.gres.core.jmetal4.metrics.Cbcs;
import br.ufpr.dinf.gres.core.jmetal4.metrics.Elegance;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CbcsPersistence {

    private Connection connection;

    public CbcsPersistence(Connection connection) {
	this.connection = connection;
    }

    public void save(Cbcs cBcs) {

	String executionID = "null";
	if (cBcs.getExecutionResults() != null)
	    executionID = cBcs.getExecutionResults().getId();

	StringBuilder query = new StringBuilder();

	query.append("insert into CbcsMetrics (cBcs, execution_id, is_all, experiement_id, id_solution) values (");
	query.append(cBcs.getCbcs());
	query.append(",");
	query.append(executionID);
	query.append(",");
	if (cBcs.getExecutionResults() == null)
	    query.append("1");
	else
	    query.append("0");
	query.append(",");
	query.append(cBcs.getExperiement().getId());
	query.append(",");
	query.append(cBcs.getIdSolution());
	query.append(")");

	try {
	    Statement statement = connection.createStatement();
	    statement.executeUpdate(query.toString());
	} catch (SQLException ex) {
	    Logger.getLogger(Elegance.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
}
