import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Mage")
public class Mage {
    @Id
    @Column(name = "name")
    private String name;

    private int level;

    public Mage(){}

    public Mage(String name, int level){
        this.name = name;
        this.level = level;
    }

    public Mage(String name, int level, Tower tower){
        this.name = name;
        this.level = level;
        this.tower = tower;
    }

    @ManyToOne
    private Tower tower;

    public String getId() {
        return name;
    }
    public void setId(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getLevel() {
        return level;
    }
    public void setLevel(int level) {
        this.level = level;
    }
    public void setTower(Tower tower){
        this.tower = tower;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mage mage = (Mage) o;
        return level == mage.level && Objects.equals(name, mage.name) && Objects.equals(tower, mage.tower);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level, tower);
    }

    @Override
    public String toString() {
        return "Mage{" +
                "name='" + name + '\'' +
                ", level=" + level +
                ", tower=" + tower +
                '}';
    }
}
