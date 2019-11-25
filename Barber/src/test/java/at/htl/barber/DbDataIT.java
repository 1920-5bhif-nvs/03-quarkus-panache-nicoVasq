package at.htl.barber;

import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.sql.DataSource;

import static org.assertj.db.api.Assertions.assertThat;

@QuarkusTest
public class DbDataIT {

    @Inject
    DataSource dataSource;

    @Test
    public void test01_shop_data_is_correct(){
        Table table = new Table(dataSource, "barbershop");

        assertThat(table).column("name")
                .value().isEqualTo("Klipp")
                .value().isEqualTo("DM")
                .value().isEqualTo("Schnittzone");
    }

    @Test
    public void test02_person_data_is_correct(){
        Table table = new Table(dataSource, "person");

        assertThat(table).column("name")
                .value().isEqualTo("Sophie")
                .value().isEqualTo("Dianna")
                .value().isEqualTo("Tom")
                .value().isEqualTo("Max");
    }

    @Test
    public void test03_equipment_data_is_correct(){
        Table table = new Table(dataSource, "equipment");

        assertThat(table).column("name")
                .value().isEqualTo("Maxwell")
                .value().isEqualTo("Schwarzkopf")
                .value().isEqualTo("Loreal")
                .value().isEqualTo("Syoss");
    }

    @Test
    public void test04_blowdryer_data_is_correct(){
        Table table = new Table(dataSource, "blowdryer");

        assertThat(table)
                .column("hascoolshot")
                .value().isEqualTo(true)
                .column("heatsettings")
                .value().isEqualTo(3);
    }


}
