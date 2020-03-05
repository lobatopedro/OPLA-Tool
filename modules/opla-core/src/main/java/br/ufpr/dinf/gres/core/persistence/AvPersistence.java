package br.ufpr.dinf.gres.core.persistence;

import br.ufpr.dinf.gres.core.jmetal4.metrics.Av;
import br.ufpr.dinf.gres.core.jmetal4.metrics.Elegance;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AvPersistence {

    private Connection connection;

    public AvPersistence(Connection connection) {
	this.connection = connection;
    }

    public void save(Av aV) {

	String executionID = "null";
	if (aV.getExecutionResults() != null)
	    executionID = aV.getExecutionResults().getId();

	StringBuilder query = new StringBuilder();

	query.append("insert into AvMetrics (aV, execution_id, is_all, experiement_id, id_solution) values (");
	query.append(aV.getAv());
	query.append(",");
	query.append(executionID);
	query.append(",");
	if (aV.getExecutionResults() == null)
	    query.append("1");
	else
	    query.append("0");
	query.append(",");
	query.append(aV.getExperiement().getId());
	query.append(",");
	query.append(aV.getIdSolution());
	query.append(")");

	try {
	    Statement statement = connection.createStatement();
	    statement.executeUpdate(query.toString());
	} catch (SQLException ex) {
	    Logger.getLogger(Elegance.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
}
