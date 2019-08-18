package infraestructure.repository;

import com.google.inject.Inject;
import domain.Gasto;
import infraestructure.acl.GastoMapper;
import io.vavr.collection.List;
import io.vavr.concurrent.Future;
import io.vavr.control.Option;
import org.skife.jdbi.v2.DBI;
import play.api.db.Database;

public class GastoRepository {

  private DBI db;

  @Inject
  public GastoRepository(Database db) {
    this.db = new DBI(db.dataSource());
  }

  public List<Gasto> listAll() {
    return List.ofAll(db.onDemand(GastoDAO.class).listAll())
      .map(GastoMapper::recordToGasto);
  }

  public Option<Gasto> find(int id) {
    return Option.of(db.onDemand(GastoDAO.class).find(id))
      .map(GastoMapper::recordToGasto);
  }

  public Future<Gasto> save(Gasto gasto) {
    GastoRecord record = GastoMapper.gastoToRecord(gasto);
    return Future.of(() -> db.onDemand(GastoDAO.class).insert(record))
      .map(GastoMapper::recordToGasto);
  }

  public Future<Option<Gasto>> update(Gasto gasto) {
    GastoRecord record = GastoMapper.gastoToRecord(gasto);
    return Future.of(() -> Option.of(db.onDemand(GastoDAO.class).update(record))
      .map(GastoMapper::recordToGasto));
  }

  public Future<Option<Gasto>> delete(int id) {
    return Future.of(() -> Option.of(db.onDemand(GastoDAO.class).delete(id))
      .map(GastoMapper::recordToGasto));
  }

}
