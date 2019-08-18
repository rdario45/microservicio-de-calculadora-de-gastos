package controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Inject;
import controllers.dto.GastoDTO;
import infraestructure.acl.GastoMapper;
import infraestructure.acl.GastoValidator;
import infraestructure.repository.GastoRepository;
import io.vavr.collection.List;
import io.vavr.control.Either;
import io.vavr.control.Try;
import play.Logger;
import play.libs.Json;
import play.mvc.Result;
import play.mvc.Results;

import java.util.function.Function;

import static play.mvc.Http.Context.Implicit.request;
import static play.mvc.Results.*;

public class GastoController {

  private GastoRepository repository;

  @Inject
  public GastoController(GastoRepository repository) {
    this.repository = repository;
  }

  public Result index() {
    return ok(Json.toJson(repository.listAll().map(GastoMapper::gastoToDTO)));
  }

  public Result find(int index) {
    return repository.find(index)
      .map(GastoMapper::gastoToJsonDTO)
      .toEither(getNotFoundJsonMessage(index))
      .fold(Results::notFound, Results::ok);
  }

  public Result save() {
    JsonNode json = request().body().asJson();
    Logger.info("POST /save body:" + json.toString());
    return getGastoDTO(json.get("body"))
      .flatMap(GastoValidator::validate)
      .mapLeft(this::getValidationErrorMessage)
      .mapLeft(Results::badRequest)
      .map(gasto -> repository.save(gasto))
      .flatMap( future -> future.onFailure(throwable -> Logger.error("Error saving gasto!", throwable))
        .toEither(internalServerError(getJsonErrorMessage("Error saving gasto!")))
      ).map(GastoMapper::gastoToJsonDTO)
      .fold(result -> result, Results::ok);
  }

  public Result update(){
    JsonNode json = request().body().asJson();
    Logger.info("PATCH /update body:" + json.toString());
    return getGastoDTO(json.get("body"))
      .flatMap(GastoValidator::validate)
      .mapLeft(this::getValidationErrorMessage)
      .mapLeft(Results::badRequest).map(gasto -> repository.update(gasto))
      .flatMap(future ->
        future.map(option -> option.toEither(notFound(getJsonErrorMessage("Not Found"))))
          .onFailure(throwable -> Logger.error("Error updating gasto!", throwable))
          .toEither(internalServerError(getJsonErrorMessage("Error updating gasto!")))
          .flatMap(Function.identity()))
      .map(GastoMapper::gastoToJsonDTO)
      .fold(result -> result, Results::ok);
  }

  public Result delete(int id) {
    return repository.delete(id)
      .map(option -> option.toEither(notFound(getJsonErrorMessage("Not Found"))))
      .onFailure(throwable -> Logger.error("Error removing gasto!", throwable))
      .toEither(internalServerError(getJsonErrorMessage("Error removing gasto!")))
      .flatMap(Function.identity())
      .map(GastoMapper::gastoToJsonDTO)
      .fold(result -> result, Results::ok);
  }

  private Either<List<String>, GastoDTO> getGastoDTO(JsonNode json) {
    Logger.info(json.toString());
    return Try.of(() -> Json.fromJson(json, GastoDTO.class))
      .onFailure(throwable -> Logger.error("invalid json", throwable))
      .toEither(List.of("invalid json"));
  }

  private ObjectNode getValidationErrorMessage(List<String> errors) {
    return Json.newObject()
      .put("message", "Validation errors!")
      .put("fields", String.join(", ", errors));
  }

  private ObjectNode getNotFoundJsonMessage(int index) {
    return Json.newObject().put("message", "id " + index + " not found");
  }

  private ObjectNode getJsonErrorMessage(String message) {
    return Json.newObject().put("message", message);
  }

}
