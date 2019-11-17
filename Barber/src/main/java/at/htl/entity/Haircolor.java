package at.htl.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Haircolor extends Equipment {

    private String color;
    private boolean isSemiPermanent;

    //region Constructor
    public Haircolor() {
    }

    public Haircolor(String name, Integer quantity, String color, boolean isSemiPermanent) {
        super(name, quantity);
        this.color = color;
        this.isSemiPermanent = isSemiPermanent;
    }
    //endregion

    //region Entity methods
    public static List<Haircolor> findAllHaircolors(){
        return Haircolor.findAll().list();
    }

    public static Haircolor findByName(String name){
        return find("name", name).firstResult();
    }
    //endregion

    //region Getter Setter
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public boolean isSemiPermanent() {
        return isSemiPermanent;
    }

    public void setSemiPermanent(boolean semiPermanent) {
        isSemiPermanent = semiPermanent;
    }
    //endregion
}
