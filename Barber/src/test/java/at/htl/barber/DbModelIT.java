package at.htl.barber;

import io.quarkus.test.junit.QuarkusTest;
import org.assertj.db.type.Table;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.sql.DataSource;

import static org.assertj.db.api.Assertions.assertThat;

@QuarkusTest
public class DbModelIT {

    @Inject
    DataSource dataSource;

    @Test
    public void test01_number_of_colums_correct(){
        Table table = new Table(dataSource, "barbershop");
        assertThat(table).hasNumberOfColumns(2);

        table = new Table(dataSource, "blowdryer");
        assertThat(table).hasNumberOfColumns(3);

        table = new Table(dataSource, "customer");
        assertThat(table).hasNumberOfColumns(2);

        table = new Table(dataSource, "employee");
        assertThat(table).hasNumberOfColumns(3);

        table = new Table(dataSource, "haircolor");
        assertThat(table).hasNumberOfColumns(3);

        table = new Table(dataSource, "person");
        assertThat(table).hasNumberOfColumns(2);

        table = new Table(dataSource, "service");
        assertThat(table).hasNumberOfColumns(6);
    }

    @Test
    public void test02_data_types_correct(){
        Table table = new Table(dataSource, "barbershop");
        assertThat(table)
                .column("name").isText(true);

        table = new Table(dataSource, "blowdryer");
        assertThat(table)
                .column("hascoolshot").isBoolean(true)
                .column("heatsettings").isNumber(true);

        table = new Table(dataSource, "customer");
        assertThat(table)
                .column("lastvisited").isDate(true);

    }
}
