package de.tro.development.dao.interf;

import java.util.List;

import de.tro.development.model.Category;

public interface CategoryDAOInterface {
	public List<String> getCategories();
	public Category getCategoryByName(String name);
}
