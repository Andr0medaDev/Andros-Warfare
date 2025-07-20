package top.andro.a_warfare.item;

public class ScorchedWeapon extends GunItem{
    public ScorchedWeapon(Properties properties) {
        super(properties);
    }
    @Override
    public boolean isFireResistant() {
        return true;
    }
}
