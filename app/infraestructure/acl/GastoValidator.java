package infraestructure.acl;

import controllers.dto.GastoDTO;
import domain.Gasto;
import domain.Tipo;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Try;
import io.vavr.control.Validation;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.math.BigDecimal;

public class GastoValidator {

  public static Either<List<String>, Gasto> validate(GastoDTO dto) {
    return Validation.combine(
      Validation.valid(dto.getId()),
      validateTitulo(dto.getTitulo()),
      validateTipo(dto.getTipo()),
      validateDescripcion(dto.getDescripcion()),
      validateValor(dto.getValor()),
      validateFecha(dto.getFecha())
    ).ap(GastoBuilder::build)
      .toEither()
      .mapLeft(List::ofAll);
  }

  static Validation<String, String> validateTitulo(String titulo) {
    return StringUtils.isNotEmpty(titulo) && titulo.length() <= 50 ? Validation.valid(titulo) : Validation.invalid("titulo");
  }

  static Validation<String, Tipo> validateTipo(String tipo) {
    Tipo value = Tipo.of(tipo);
    return value != Tipo.DESCONOCIDO ? Validation.valid(value) : Validation.invalid("tipo");
  }

  static Validation<String, String> validateDescripcion(String descripcion) {
    return StringUtils.isEmpty(descripcion) || descripcion.length() <= 100 ? Validation.valid(descripcion) : Validation.invalid("descripcion");
  }

  static Validation<String, BigDecimal> validateValor(String valor) {
    return Try.of(() -> new BigDecimal(valor))
      .toEither("valor")
      .toValidation();
  }

  static Validation<String, DateTime> validateFecha(String fecha) {
    return Try.of(() -> DateTime.parse(fecha))
      .toEither("fecha")
      .toValidation();
  }
}
