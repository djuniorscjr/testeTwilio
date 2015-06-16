package models;

import org.joda.time.DateTime;
import play.data.validation.Constraints;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Domingos Junior on 15/06/2015.
 */
@Entity
public class Pessoa {

    @Id
    @GeneratedValue
    public Long id;

    @Constraints.Required
    public String telefone;
    public DateTime data;
}
