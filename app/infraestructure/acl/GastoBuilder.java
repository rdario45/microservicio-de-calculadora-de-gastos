package infraestructure.acl;

import domain.Gasto;
import domain.Tipo;
import org.joda.time.DateTime;

import java.math.BigDecimal;

public class GastoBuilder {

  public static Gasto build(String titulo,
                            Tipo tipo,
                            String descripcion,
                            BigDecimal valor,
                            DateTime fecha) {
    return new Gasto(0, titulo, tipo, descripcion, valor, fecha);
  }

  public static Gasto build(int id,
                            String titulo,
                            Tipo tipo,
                            String descripcion,
                            BigDecimal valor,
                            DateTime fecha) {
    return new Gasto(id, titulo, tipo, descripcion, valor, fecha);
  }
}