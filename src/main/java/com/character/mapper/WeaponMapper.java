package com.character.mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.character.model.srd.APIReference;
import com.character.model.srd.SRDWeapon;
import com.character.persistence.entity.Property;
import com.character.persistence.entity.Weapon;

@Component
public class WeaponMapper {
	
	@PersistenceContext
	EntityManager entityManager;

	public Weapon mapWeaponEntityFromSRD(SRDWeapon weapon) {
		
		Weapon weaponEntity = new Weapon();
		weaponEntity.setWeaponName(weapon.getName());
		weaponEntity.setWeaponCategory(weapon.getWeapon_category());
		weaponEntity.setWeaponRange(weapon.getWeapon_range());
		weaponEntity.setCategoryRange(weapon.getCategory_range());
		weaponEntity.setCost(String.valueOf(weapon.getCost() != null ? weapon.getCost().getQuantity() + " " + weapon.getCost().getUnit(): "free"));
		weaponEntity.setDamageDice(weapon.getDamage()!= null ? weapon.getDamage().getDamage_dice() : "Special");
		weaponEntity.setTwoHandedDamageDice(weapon.getTwoh_damage() != null ? weapon.getTwoh_damage().getDamage_dice() : null);
		weaponEntity.setDamageType(weapon.getDamage() != null ? weapon.getDamage().getDamage_type().getName() : "Special");
		weaponEntity.setNormalRange(weapon.getRange().getNormal());
		weaponEntity.setLongRange(weapon.getRange().getLongRange());
		weaponEntity.setWeight(weapon.getWeight());
		weaponEntity.setProperties(getPropertiesForWeapon(weapon));

		return weaponEntity;
	}
	
	private Set<Property> getPropertiesForWeapon(SRDWeapon weapon) {
		List<APIReference> properties = weapon.getProperties();
		Set<Property> finalProperties = new HashSet<>();
		for(APIReference propertyReference : properties) {
			String propertyName = propertyReference.getName();
			
			Property property = null;
			
			if("Reach".equalsIgnoreCase(propertyName)) {
				property = entityManager.getReference(Property.class, 1L);
			} else if ("Special".equalsIgnoreCase(propertyName)) {
				property = entityManager.getReference(Property.class, 2L);
			} else if ("Monk".equalsIgnoreCase(propertyName)) {
				property = entityManager.getReference(Property.class, 3L);
			} else if ("Thrown".equalsIgnoreCase(propertyName)) {
				property = entityManager.getReference(Property.class, 4L);
			} else if ("Heavy".equalsIgnoreCase(propertyName)) {
				property = entityManager.getReference(Property.class, 5L);
			} else if ("Two-Handed".equalsIgnoreCase(propertyName)) {
				property = entityManager.getReference(Property.class, 6L);
			} else if ("Loading".equalsIgnoreCase(propertyName)) {
				property = entityManager.getReference(Property.class, 7L);
			} else if ("Versatile".equalsIgnoreCase(propertyName)) {
				property = entityManager.getReference(Property.class, 8L);
			} else if ("Finesse".equalsIgnoreCase(propertyName)) {
				property = entityManager.getReference(Property.class, 9L);
			} else if ("Light".equalsIgnoreCase(propertyName)) {
				property = entityManager.getReference(Property.class, 10L);
			} else if ("Ammunition".equalsIgnoreCase(propertyName)) {
				property = entityManager.getReference(Property.class, 11L);
			}
			finalProperties.add(property);
		}
		return finalProperties;
	}
}
