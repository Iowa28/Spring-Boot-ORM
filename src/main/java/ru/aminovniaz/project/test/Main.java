package ru.aminovniaz.project.test;

import ru.aminovniaz.project.model.Category;
import ru.aminovniaz.project.util.MyEntityManager;
import ru.aminovniaz.project.util.MyEntityManagerImpl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Main {

    public static void sayHi() {
        System.out.println("Say hi");
    }

    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MyEntityManager entityManager = new MyEntityManagerImpl();
//
//        Method method = Main.class.getMethod("sayHi");
//        method.invoke(null);

//        List<Category> categories = entityManager.find(Category.class);
//        if (categories.size() != 0) {
//            for (Category category : categories) {
//                System.out.println(category.getName());
//            }
//        }

        Category category = entityManager.findById(Category.class, 1);

        if (category != null) {
            System.out.println(category.getName());
        }


//        Specification<Category> spec = (Specification<Category>) (root, criteriaQuery, criteriaBuilder)
//                -> criteriaBuilder.like(root.get("name"), "Cl%");
//        //List<Category> filteredCategories = entityManager.find(Category.class, spec);
//
//        String condition1 = "name LIKE 'Cl%'";
//        String condition2 = "id > 2";
//        List<String> conditions = new ArrayList<>();
//        conditions.add(condition1);
//        conditions.add(condition2);
//
//        entityManager.find(Category.class, conditions, "AND");
    }
}

