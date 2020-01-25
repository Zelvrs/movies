package com.gazel.movies;

public class Movie {
    int id;
    String tittle;
    String overview;
    String poster;
    String backdrop;

    public Movie(int id, String tittle, String overview, String poster, String backdrop) {
        this.id = id;
        this.tittle = tittle;
        this.overview = overview;
        this.poster = poster;
        this.backdrop = backdrop;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(String backdrop) {
        this.backdrop = backdrop;
    }
}
