package homework.homework3_2.model;

import java.util.Objects;

public class Ingredient {
    private String name;
    private int amount;
    private String measure;

    public Ingredient(String name, int amount, String measure) {
        setName(name);
        setAmount(amount);
        setMeasure(measure);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name != null && !name.isEmpty()){
            this.name = name;
        }
        else{
            this.name = "default";
        }
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        if(amount > 0){
            this.amount = amount;
        }
        else{
            this.amount = 0;
        }
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        if(measure != null && !measure.isEmpty()){
            this.measure = measure;
        }
        else{
            this.measure = "по вкусу";
        }
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", measure='" + measure + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return amount == that.amount && Objects.equals(name, that.name) && Objects.equals(measure, that.measure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, amount, measure);
    }
}
