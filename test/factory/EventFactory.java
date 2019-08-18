package factory;

import com.github.javafaker.Faker;
import domain.Gasto;
import domain.Tipo;
import org.joda.time.DateTime;

public class EventFactory {

    Faker faker ;
    private int id;
    private String name;
    private Tipo tipo;
    private String place;
    private String address;
    private DateTime startDate;
    private DateTime finishDate;

    public EventFactory() {
        Tipo[] tipoValues = Tipo.values();
        faker = new Faker();
        this.id = faker.number().randomDigit();
        this.name = faker.gameOfThrones().house();
        this.tipo = Tipo.values()[tipoValues.length-1] ;
        this.place = faker.gameOfThrones().city();
        this.address = faker.address().fullAddress();
        this.startDate = DateTime.now();
        this.finishDate = this.startDate.plusHours(1);
    }

    public Gasto get(){
        return new Gasto(
          this.id,
          this.name,
          this.tipo,
          this.place,
          this.address,
          this.startDate,
          this.finishDate
        );
    }

}
