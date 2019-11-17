package at.htl.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.util.List;

@Entity
public class Blowdryer extends Equipment {

    private int heatSettings;
    private boolean hasCoolShot;

    //region Constructor
    public Blowdryer() {
    }

    public Blowdryer(String name, Integer quantity, int heatSettings, boolean hasCoolShot) {
        super(name, quantity);
        this.heatSettings = heatSettings;
        this.hasCoolShot = hasCoolShot;
    }
    //endregion

    //region Entity methods
    public static List<Blowdryer> findAllBlowdryers(){
        return Blowdryer.findAll().list();
    }

    public static List<Blowdryer> findBySettings(int heatSettings){
        return find("heatSettings", heatSettings).list();
    }
    //endregion


    //region Getter Setter
    public int getHeatSettings() {
        return heatSettings;
    }

    public void setHeatSettings(int heatSettings) {
        this.heatSettings = heatSettings;
    }

    public boolean isHasCoolShot() {
        return hasCoolShot;
    }

    public void setHasCoolShot(boolean hasCoolShot) {
        this.hasCoolShot = hasCoolShot;
    }
    //endregion
}
