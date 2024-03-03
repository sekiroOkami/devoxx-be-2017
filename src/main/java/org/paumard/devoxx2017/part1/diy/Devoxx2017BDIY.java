package org.paumard.devoxx2017.part1.diy;

import org.paumard.devoxx2017.model.Article;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.*;
public class Devoxx2017BDIY {
    public static void main(String[] args) {
        Set<Article> articles = Article.readAll();

        // # articles per year
        Map<Integer, Long> numberOfArticlesPerYear =
         articles.stream()
                 .filter(article -> article.getInceptionYear() >1900)
                 .collect(
                         Collectors.groupingBy(
                                 Article::getInceptionYear,
                                 Collectors.counting()
                         ));
        System.out.println("numberOfArticlesPerYear = " + numberOfArticlesPerYear);

        // get the year with the most articles
        Stream<Map.Entry<Integer, Long>> streamOf =
        numberOfArticlesPerYear.entrySet().stream();

        Map.Entry<Integer, Long> firstMapEntryWithTheMaxArticle =
                numberOfArticlesPerYear.entrySet().stream()
                        .max(Comparator.comparing(mapEntry -> mapEntry.getValue()))
                        .get();
        System.out.println("firstMapEntryWithTheMaxArticle = " + firstMapEntryWithTheMaxArticle);

        Long firstMaxArticles =
        numberOfArticlesPerYear.entrySet().stream()
                .map(integerLongEntry -> integerLongEntry.getValue())
                .max(Comparator.naturalOrder())
                .get();

        System.out.println("yearWithMaxArticle = " + firstMaxArticles);

//        Map<Long, List<Map.Entry<Integer, Long>>> collect =

        Map.Entry<Long, List<Map.Entry<Integer, Long>>> allTheMaxesNumberOfArticlePerYear =
                numberOfArticlesPerYear
                .entrySet().stream() // Stream<Map.entry<Integer, Long>
                .collect(
                        Collectors.groupingBy(
                                // Map.entry<Integer, Long>
                                Map.Entry::getValue
                        )
                )// Map<Long, List<Map.entry<Integer, Long>>>
                .entrySet().stream() // Map.entry<Long, List<Map.entry<Integer, Long>>>
                .max(Comparator.comparing(
                        Map.Entry::getKey
                )) // Optional<Map.entry<Long, List<Map.Entry<Integer, Long>>>>
                .get();

        System.out.println("allTheMaxesNumberOfArticlePerYear = " + allTheMaxesNumberOfArticlePerYear);

    }

}
