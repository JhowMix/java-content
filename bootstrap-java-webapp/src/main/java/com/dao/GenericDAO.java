package com.dao;

import java.util.List;

public interface GenericDAO {
	
	void create(Object object) throws Exception;
	List<?> read();		
	void update(Object object);
	void delete(Object object);
}
