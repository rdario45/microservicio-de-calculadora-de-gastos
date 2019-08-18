package domain;

import io.vavr.collection.List;

public enum Tipo {
    DESCONOCIDO,
    COMPRA,
    RETIRO,
    SERVICIOS,
    OTROS;

    public static Tipo of (String name) {
        return List.of(Tipo.values()).find(t -> t.name().equals(name)).getOrElse(Tipo.DESCONOCIDO);
    }
}
