package io.zgc.design.patterns.structural.adapter.clazz;


import io.zgc.design.patterns.structural.adapter.MoviePlayer;

public class MainTest {

    public static void main(String[] args) {

        MoviePlayer player = new MoviePlayer();
        JPMoviePlayerAdapter adapter = new JPMoviePlayerAdapter(player);
//
//
        adapter.play();
        //player.play();

    }
}
