package org.paumard.devoxx2017.part1.diy;

import org.paumard.devoxx2017.model.Article;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Set;
import java.util.stream.*;

public class Devoxx2017ADIY {
    public static void main(String[] args) {
        Set<Article> articles = Article.readAll();
        long count = articles.stream()
                .collect(Collectors.counting());
//                .count();
        System.out.println("count = " + count);

        int minYear =
        articles.stream()
                .filter(article -> article.getInceptionYear() > 1900)
                .map(Article::getInceptionYear)
                .collect(Collectors.minBy(Comparator.naturalOrder()))
//                .min(Comparator.naturalOrder())
                .get();
        System.out.println("minYear = " + minYear);

        int maxYear =
                articles.stream()
                        .filter(article -> article.getInceptionYear() > 1900)
                        .map(Article::getInceptionYear)
                        .max(Comparator.naturalOrder())
                        .get();
        System.out.println("maxYear = " + maxYear);

        IntSummaryStatistics intSummaryStatistics = articles.stream()
                .filter(article -> article.getInceptionYear() > 1900)
                .mapToInt(Article::getInceptionYear)
                .summaryStatistics();// available with IntStream
        System.out.println("intSummaryStatistics = " + intSummaryStatistics);

        intSummaryStatistics =
        articles.stream()
                .filter(article -> article.getInceptionYear() > 1900)
                .collect(Collectors.summarizingInt(Article::getInceptionYear)); // available with Stream<T>
        System.out.println("intSummaryStatistics = " + intSummaryStatistics);

        String collect = articles.stream()
                .filter(article -> article.getInceptionYear() == 1960)
                .map(Article::getTitle)
                .collect(Collectors.joining(", "));
        System.out.println("collect = " + collect);
    }
}
