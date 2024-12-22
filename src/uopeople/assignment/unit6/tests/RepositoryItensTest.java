package uopeople.assignment.unit6.tests;

import java.util.HashSet;
import uopeople.assignment.unit1.PrintUtil;
import uopeople.assignment.unit6.models.LibraryItemBase;
import uopeople.assignment.unit6.repositories.RepositoryItems;
import uopeople.assignment.utils.TypeUtil;

public abstract class RepositoryItensTest<T extends LibraryItemBase> {

    private RepositoryItems<T> repository;
    private Class<T> itemType;

    private final HashSet<String> passedMethods = new HashSet<String>();
    private final HashSet<String> failMethods = new HashSet<String>();

    @SuppressWarnings("unchecked")
    public RepositoryItensTest() {

        // Dynamically determines the type of T using TypeUtil
        // Here I could get the type of T using reflection because this class will be extended, 
        // without extending I couldn't get the type of T
        this.itemType = (Class<T>) TypeUtil.getGenericType(this);
        this.repository = new RepositoryItems<T>(this.itemType);
    }

    public void run() {

        System.out.println("Running tests for " + this.itemType + " Repository");

        testAdd();
        testRemove();
        testRemoveById();
        testGetAll();
        showResults();
    }

    protected void showResults() {

        PrintUtil.printSuccessMessage("Passed methods:");
        for (String method : this.passedMethods) {
            PrintUtil.printSuccessMessage(method + " passed");
        }

        if (this.failMethods.isEmpty()) {
            PrintUtil.printSuccessMessage("All tests passed");
            return;
        } else {

            PrintUtil.printFailMessage("Failed methods:");

            for (String method : failMethods) {
                PrintUtil.printFailMessage( method + " failed");
           }
        }
    }

    private void testAdd() {

        T item = createTestItem();
        this.repository.addItem(item);

        assertTrue(item.getId() > 0, "addItem");
        assertTrue(this.repository.getItemById(item.getId()) == item, "RepositoryItems.addItem");
    }

    private void testRemove() {

        T item = createTestItem();

        // first add the item
        assertTrue(this.repository.addItem(item), "addItem");

        // check if the item was added
        assertTrue(this.repository.getItemById(item.getId()) == item, "addItem");

        // remove item, first time should return true,
        assertTrue(this.repository.removeItem(item), "removeItem");

        // second time should return false

        assertTrue(this.repository.removeItem(item) == false, "removeItem");

        // check if the item was removed
        assertTrue(this.repository.getItemById(item.getId()) == null, "removeItem");
    }

    public void testRemoveById() {

        T item = createTestItem();

        // first add the item
        assertTrue(this.repository.addItem(item), "addItem");

        // check if the item was added
        assertTrue(this.repository.getItemById(item.getId()) == item, "addItem");

        // remove item, first time should return true,
        assertTrue(this.repository.removeItemById(item.getId()), "removeItemById");

        // second time should return false
        assertTrue(this.repository.removeItemById(item.getId()) == false, "removeItemById");

        // check if the item was removed
        assertTrue(this.repository.getItemById(item.getId()) == null, "removeItemById");

    }

    private void testGetAll() {

        this.repository.clear();

        T item1 = createTestItem();
        T item2 = createTestItem();
        T item3 = createTestItem();

        assertTrue(this.repository.addItem(item1), "addItem");
        assertTrue(this.repository.addItem(item2), "addItem");
        assertTrue(this.repository.addItem(item3), "addItem");

        assertTrue(this.repository.getAll().size() == 3, "getAll");
    }

    private void assertTrue(boolean condition, String methodName) {

        String fullMethodPath = String.format("%s<%s>.%s",
                this.repository.getClass().getSimpleName(),
                this.itemType.getSimpleName(),
                methodName);

        if (!condition) {
            System.out.println("Failed: " + fullMethodPath);
            this.failMethods.add(fullMethodPath);
        } else {
            this.passedMethods.add(fullMethodPath);
        }
 
    }

    protected abstract T createTestItem();
}
