package infraestructure.repository;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(GastoMapperDAO.class)
public interface GastoDAO {

    @SqlQuery("SELECT * FROM gastos")
    List<GastoRecord> listAll();

    @SqlQuery("SELECT * FROM gastos WHERE id = :id")
    GastoRecord find(@Bind("id") int id);

    @SqlQuery("INSERT INTO gastos " +
      "(titulo, " +
      "tipo, " +
      "descripcion, " +
      "valor, " +
      "fecha) " +
      "VALUES(" +
      ":r.titulo, " +
      ":r.tipo, " +
      ":r.descripcion, " +
      ":r.valor, " +
      ":r.fecha) RETURNING *")
    GastoRecord insert(@BindBean("r") GastoRecord record);

    @SqlQuery("UPDATE gastos SET " +
      "titulo = :r.titulo, " +
      "tipo = :r.tipo, " +
      "descripcion = :r.descripcion, " +
      "valor = :r.valor, " +
      "fecha = :r.fecha " +
      "WHERE id = :r.id RETURNING *")
    GastoRecord update(@BindBean("r") GastoRecord record);

    @SqlQuery("DELETE FROM gastos WHERE id = :id  RETURNING *")
    GastoRecord delete(@Bind("id") int id);

}
