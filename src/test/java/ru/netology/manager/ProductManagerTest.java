package ru.netology.manager;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.TShirt;
import ru.netology.exception.NotFoundException;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    ProductRepository repository = new ProductRepository();
    private Product first = new TShirt(1, "lacoste", 2990, "blue", "s");
    private Product second = new TShirt(2, "adidas", 1900, "white", "m");
    private Product third = new Book(3, "Capitan", 280, "Pushkin", 140, 1836);
    private Product fourth = new Book(4, "Burned", 340, "Kulukin", 231, 2019);

    ProductManager manager = new ProductManager (repository);

    @Test
    public void shouldRemoteById() {
        int id = 3;
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);

        manager.removeById(id);
        Product[] actual = manager.getAll();
        Product[] expected = new Product[]{fourth, second, first};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldRemoteByIdWithException() {
        int id = 5;
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);

        manager.removeById(id);
        assertThrows(NotFoundException.class, () -> manager.removeById(id));
    }
}