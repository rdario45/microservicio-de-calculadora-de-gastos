package infraestructure.repository;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GastoMapperDAO implements ResultSetMapper<GastoRecord> {
    @Override
    public GastoRecord map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new GastoRecord(
          r.getInt("id"),
          r.getString("titulo"),
          r.getString("tipo"),
          r.getString("descripcion"),
          r.getBigDecimal("valor"),
          r.getTimestamp("fecha")
        );
    }
}
