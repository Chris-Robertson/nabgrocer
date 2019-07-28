package au.com.nabgrocer.repository;

import au.com.nabgrocer.model.GroceryTag;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroceryTagRepository extends PagingAndSortingRepository<GroceryTag, Long> {

    GroceryTag findByTagId(long tagId);

    GroceryTag findByTagName(String name);

}
