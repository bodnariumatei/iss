package com.iss.theatre.service;

import com.iss.theatre.model.Show;
import com.iss.theatre.model.User;
import com.iss.theatre.repository.ShowsRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class AdminService {
    private ShowsRepository showsRepo;

    public AdminService(ShowsRepository showsRepo) {
        this.showsRepo = showsRepo;
    }

    public AdminService(){
        this.showsRepo = new ShowsRepository();
    }

    public Iterable<Show> getAllShows() {
        List<Show> shows = (List<Show>) showsRepo.getAll();
        return shows.stream().sorted(Comparator.comparing(Show::getName)).collect(Collectors.toList());
    }

    public Show addShow(String name, int duration) {
        return showsRepo.store(new Show(name, duration));
    }

    public Show removeShow(int id) {
        return showsRepo.delete(id);
    }
}
