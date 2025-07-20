package top.andro.a_warfare.item;

public class ScorchedEnergyGunItem extends EnergyGunItem{
    public ScorchedEnergyGunItem(Properties properties, int capacity) {
        super(properties, capacity);
    }
    @Override
    public boolean isFireResistant() {
        return true;
    }
}
