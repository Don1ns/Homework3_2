package homework.homework3_2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ingredient {
    private String name;
    private int amount;
    private String measure;

    public void setName(String name) {
        if(!StringUtils.isBlank(name) && !StringUtils.isEmpty(name)) {
            this.name = name;
        }
    }

    @Override
    public String toString() {
        return name + " - " + amount + " " + measure + "\n";
    }
}
