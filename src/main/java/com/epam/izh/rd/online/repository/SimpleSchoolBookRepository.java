package com.epam.izh.rd.online.repository;

import com.epam.izh.rd.online.entity.SchoolBook;

import java.util.Arrays;

public class SimpleSchoolBookRepository implements BookRepository<SchoolBook> {
    private SchoolBook[] schoolBooks = new SchoolBook[0];

    @Override
    public boolean save(SchoolBook book) {
        if (schoolBooks.length == 0) {
            schoolBooks = new SchoolBook[]{book};
            return true;
        } else {
            int countOfBooks = schoolBooks.length + 1;
            schoolBooks = Arrays.copyOf(schoolBooks, countOfBooks);
            schoolBooks[countOfBooks - 1] = book;
            return true;
        }
    }

    @Override
    public SchoolBook[] findByName(String name) {
        int countOfResult = 0;
        for (SchoolBook i : schoolBooks) {
            if (name.equals(i.getName())) {
                countOfResult++;
            }
        }
        SchoolBook[] result = new SchoolBook[countOfResult];
        for (int i = 0, j = 0; i < schoolBooks.length; i++) {
            if (name.equals(schoolBooks[i].getName())) {
                result[j] = schoolBooks[i];
                j++;
            }
        }
        return result;
    }

    @Override
    public boolean removeByName(String name) {
        while (findByName(name).length > 0) {
            int indexForRemove = 0;
            for (int i = 0; i < schoolBooks.length; i++) {
                if (name.equals(schoolBooks[i].getName())) {
                    indexForRemove = i;
                    break;
                }
            }
            for (int i = indexForRemove; i < schoolBooks.length - 1; i++) {
                schoolBooks[i] = schoolBooks[i + 1];
            }
            schoolBooks = Arrays.copyOf(schoolBooks, schoolBooks.length - 1);
            if (findByName(name).length == 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int count() {
        return schoolBooks.length;
    }
}
