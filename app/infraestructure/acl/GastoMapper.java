package infraestructure.acl;

import com.fasterxml.jackson.databind.JsonNode;
import controllers.dto.GastoDTO;
import domain.Gasto;
import domain.Tipo;
import infraestructure.repository.GastoRecord;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import play.libs.Json;

import java.sql.Timestamp;

public class GastoMapper {

    public static GastoDTO gastoToDTO(Gasto gasto) {
        return new GastoDTO(
          gasto.getId(),
          gasto.getTitulo(),
          gasto.getTipo().name(),
          gasto.getDescripcion(),
          gasto.getValor().toString(),
          gasto.getFecha().toString()
        );
    }

    public static JsonNode gastoToJsonDTO(Gasto gasto) {
        return Json.toJson(GastoMapper.gastoToDTO(gasto));
    }

    public static GastoRecord gastoToRecord(Gasto gasto) {
        return new GastoRecord(
          gasto.getId(),
          gasto.getTitulo(),
          gasto.getTipo().name(),
          gasto.getDescripcion(),
          gasto.getValor(),
          new Timestamp(gasto.getFecha().getMillis())
        );
    }

    public static Gasto recordToGasto(GastoRecord record) {
        return new Gasto(
          record.getId(),
          record.getTitulo(),
          Tipo.of(record.getTipo()),
          record.getDescripcion(),
          record.getValor(),
          new DateTime(record.getFecha(), DateTimeZone.forID("America/Bogota"))
        );
    }
}
