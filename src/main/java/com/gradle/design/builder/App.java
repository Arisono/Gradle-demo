package com.gradle.design.builder;

import com.gradle.android.retrofit.OkhttpUtils;

public class App {
	
/**
 * builder建造者模式
 * @param args
 */
public static void main(String[] args) {
    Hero mage =
        new Hero.Builder(Profession.MAGE, "Riobard").withHairColor(HairColor.BLACK)
            .withWeapon(Weapon.DAGGER).build();
    OkhttpUtils.println(mage.toString());

    Hero warrior =
        new Hero.Builder(Profession.WARRIOR, "Amberjill").withHairColor(HairColor.BLOND)
            .withHairType(HairType.LONG_CURLY).withArmor(Armor.CHAIN_MAIL).withWeapon(Weapon.SWORD)
            .build();
    OkhttpUtils.println(warrior.toString());

    Hero thief =
        new Hero.Builder(Profession.THIEF, "Desmond").withHairType(HairType.BALD)
            .withWeapon(Weapon.BOW).build();
    OkhttpUtils.println(thief.toString());
  }
}
