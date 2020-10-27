package tacos.data;

import org.springframework.data.repository.CrudRepository;

import tacos.Ingredient;

// CrudRepository is parametrized by 
// 1) entity type to persist
// 2) type of the entity ID property
public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
