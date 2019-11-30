package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.Author;

import java.util.Arrays;

public class SimpleAuthorRepository implements AuthorRepository {
    private Author[] authors = new Author[0];


    @Override
    public boolean save(Author author) {
        if (authors.length == 0) {
            authors = new Author[]{author};
            return true;
        }
        if (findByFullName(author.getName(), author.getLastName()) == null) {
            int countOfAuthors = authors.length + 1;
            authors = Arrays.copyOf(authors, countOfAuthors);
            authors[countOfAuthors] = author;
            return true;
        }
        return false;
    }

    @Override
    public Author findByFullName(String name, String lastname) {
        for (Author i : authors) {
            if (name.equals(i.getName()) && lastname.equals(i.getLastName())) {
                return i;
            }
        }
        return null;
    }

    @Override
    public boolean remove(Author author) {
        if (findByFullName(author.getName(), author.getLastName()) == author) {
            int index = Arrays.asList(authors).indexOf(author);
            for (int i = index; i < authors.length - 1; i++) {
                authors[i] = authors[i + 1];
            }
            authors = Arrays.copyOf(authors, authors.length - 1);
            return true;
        }
        return false;
    }

    @Override
    public int count() {
        return authors.length;
    }
}
