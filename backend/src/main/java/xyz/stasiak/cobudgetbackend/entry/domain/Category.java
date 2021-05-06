package xyz.stasiak.cobudgetbackend.entry.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
import java.util.Set;

@Document("Categories")
class Category {

    @Id
    private String id;

    private String username;

    private Set<String> expenseCategories;

    private Set<String> expenseSubcategories;

    private Set<String> earningCategories;

    String getId() {
        return id;
    }

    void setId(String id) {
        this.id = id;
    }

    String getUsername() {
        return username;
    }

    void setUsername(String username) {
        this.username = username;
    }

    Set<String> getExpenseCategories() {
        return expenseCategories;
    }

    void setExpenseCategories(Set<String> expenseCategories) {
        this.expenseCategories = expenseCategories;
    }

    Set<String> getExpenseSubcategories() {
        return expenseSubcategories;
    }

    void setExpenseSubcategories(Set<String> expenseSubcategories) {
        this.expenseSubcategories = expenseSubcategories;
    }

    Set<String> getEarningCategories() {
        return earningCategories;
    }

    void setEarningCategories(Set<String> earningCategories) {
        this.earningCategories = earningCategories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Category category = (Category) o;
        return Objects.equals(getId(), category.getId()) && Objects.equals(getUsername(), category.getUsername()) &&
               Objects.equals(getExpenseCategories(), category.getExpenseCategories()) &&
               Objects.equals(getExpenseSubcategories(), category.getExpenseSubcategories()) &&
               Objects.equals(getEarningCategories(), category.getEarningCategories());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getExpenseCategories(), getExpenseSubcategories(),
                            getEarningCategories());
    }

    @Override
    public String toString() {
        return "Category{" + "id='" + id + '\'' + ", username='" + username + '\'' + ", expenseCategories=" +
               expenseCategories + ", expenseSubcategories=" + expenseSubcategories + ", earningCategories=" +
               earningCategories + '}';
    }
}
