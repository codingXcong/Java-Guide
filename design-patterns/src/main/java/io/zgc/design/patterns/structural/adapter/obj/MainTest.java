package io.zgc.design.patterns.structural.adapter.obj;


import io.zgc.design.patterns.structural.adapter.MoviePlayer;

public class MainTest {
    public static void main(String[] args) {

        // MeiYanDecorator decorator = new MeiYanDecorator(manTikTok);
        JPMoviePlayerAdapter adapter = new JPMoviePlayerAdapter(new MoviePlayer());

        adapter.play();
    }

}
