package org.yearup.data.mysql;

import org.springframework.stereotype.Component;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// TODO - COMPLETE THIS

@Component
public class MySqlCategoryDao extends MySqlDaoBase implements CategoryDao
{
    public MySqlCategoryDao(DataSource dataSource)
    {
        super(dataSource);
    }

    @Override
    public List<Category> getAllCategories()

    {
        // get all categories
        List<Category> categories = new ArrayList<>();
        String something = "Select * From categories;";
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement(something);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()){
                Category category = mapRow(resultSet);
                categories.add(category);
            }
            return categories;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Category getById(int categoryId)
    {
        // get category by id
        return null;
    }

    @Override
    public Category create(Category category)
    {
        // create a new category
        return null;
    }

    @Override
    public void update(int categoryId, Category category)
    {
        // update category
    }

    @Override
    public void delete(int categoryId)
    {
        // delete category
    }

    private Category mapRow(ResultSet row) throws SQLException
    {
        int categoryId = row.getInt("category_id");
        String name = row.getString("name");
        String description = row.getString("description");

        Category category = new Category()
        {{
            setCategoryId(categoryId);
            setName(name);
            setDescription(description);
        }};

        return category;
    }

}