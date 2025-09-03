package pt.gongas.customitems.shared.util;

public interface Item<P, E, I> {

    void setPlayerMainItemHand(P player, I item);

    I getPlayerMainItemHand(P player);

    void setArmorStandMainItemHand(E equipment, I item);

    I getArmorStandMainItemHand(E equipment);

    void setArmorStandOffHandItemHand(E equipment, I item);

    I getArmorStandOffHandItemHand(E equipment);

}
