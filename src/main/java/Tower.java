import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Tower")
public class Tower {
    @Id
    @Column(name = "name")
    private String name;

    private int height;

    public Tower(){}

    public Tower(String name, int height){
        this.name = name;
        this.height = height;
        this.mages = new ArrayList<>();
    }

    @OneToMany(mappedBy = "tower", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<Mage> mages;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public List<Mage> getMages() {
        return mages;
    }
    public void setMages(List<Mage> mages) {
        this.mages = mages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tower tower = (Tower) o;
        return height == tower.height && Objects.equals(name, tower.name) && Objects.equals(mages, tower.mages);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, height, mages);
    }

    @Override
    public String toString() {
        return "Tower{" +
                "name='" + name + '\'' +
                ", height=" + height +
                ", mages=" + mages +
                '}';
    }
}
