package com.thoughtworks.bookish.specification;

import com.thoughtworks.bookish.model.Book;
import com.thoughtworks.bookish.model.Book_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

public class BookSpecs {
    public static Specification<Book> isAuthorizedBy(String author) {
        return (root, query, builder) -> builder.like(builder.lower(root.get(Book_.author)), getContainsLikePattern(author));
    }

    private static String getContainsLikePattern(String term) {
        return StringUtils.isEmpty(term) ? "%" : "%" + term.toLowerCase() + "%";
    }
}
