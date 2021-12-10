package iducs.jsp.final201712046.util;

import iducs.jsp.final201712046.model.Blog;

import java.util.Comparator;

public class DescByBlogTitle implements Comparator<Blog> {
    
    @Override
    public int compare(Blog o1, Blog o2) {
        // o2 <= o1 : 음수 / o2 > o1 : 1 이상 내림차순
        return o2.getTitle().compareTo(o1.getTitle());
       // return o1.getContent().compareTo(o1.getTitle()); // 오름차순

    }
}
