package com.thoughtworks.bookish.specification;

import com.thoughtworks.bookish.model.Book;
import com.thoughtworks.bookish.model.Book_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.sql.Date;
import java.time.LocalDate;

public class BookSpecs {
    public static Specification<Book> isAuthorizedBy(String author) {
        return (root, query, builder) -> builder.like(builder.lower(root.get(Book_.author)), getContainsLikePattern(author));
    }

    public static Specification<Book> publishedNYearsAgo(Long years) {
        return (root, query, cb) -> {
            LocalDate localDate = LocalDate.now().minusYears(years);
            return cb.lessThan(root.get(Book_.publishedAt), Date.valueOf(localDate));
        };
    }

    public static Specification<Book> hasTitleLike(String title) {
        return (root, query, builder) -> builder.like(builder.lower(root.get(Book_.title)), getContainsLikePattern(title));
    }

    private static String getContainsLikePattern(String term) {
        return StringUtils.isEmpty(term) ? "%" : "%" + term.toLowerCase() + "%";
    }
}
